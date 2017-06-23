package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChooseProdHarv;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvMalusEffect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 05/06/17.
 */
public class StartProdHarv implements Action, Blocking {

    private final AskUserCard askUser;
    private final int value;
    private final Player player;
    private final Game game;
    private Map<String, Integer> userActivateEffect;
    private ActionType actionType;
    private ProdHarvFilterCard prodHarvFilterCard;
    private  Map<String, DevelopmentCard> userAsk = new HashMap<>();

    /**
     *
     * @param cardList List of cards
     * @param askUserCardFilter The functions that choose the cards we need to ask
     * @param value The value of the production/harvest
     * @param player The player that started the Action
     */

    public StartProdHarv(List<DevelopmentCard> cardList, ActionType actionType, AskUserCard askUserCardFilter, int value, Player player, Game game)   //It should check if there is at least a "resource transformation" effect
    {
        super();
        if (cardList==null || askUserCardFilter==null)
            throw new NullPointerException();

        this.game = game;
        this.askUser = askUserCardFilter;
        this.value = value;
        this.player = player;
        this.actionType = actionType;
        if (actionType.equals(ActionType.HARVEST_ACTION))
        {
            prodHarvFilterCard = new DefaultHarverstCardSelector();
        }
        else{
            prodHarvFilterCard = new DefaultProductionCardSelector();
        }
    }

    /**
     * Starts a production/harvest
     */
    @Override
    public synchronized void execute() {
     //   game.getGameStatus().changeState(TransitionType.START_PRODHARV);

        List<ProdHarvEffect> autoExecute = new LinkedList<>();
        Map<String, List<Integer>> askUser = new HashMap<>();


        //Select the cards we need to ask

        for (DevelopmentCard developmentCard: player.getPlayerBoard().getDevelopmentCards())
        {
            if (prodHarvFilterCard.isSatisfiable(developmentCard)) {
                List<ProdHarvEffect> askUserEffects = this.askUser.askUser(developmentCard, value, player);
                List<Integer> userOptions = new LinkedList<>();

                if (askUserEffects.size() > 0)     //I need to ask
                {
                    userAsk.put(developmentCard.getPath(), developmentCard);        //name card -> developmentCard
                    List<ProdHarvEffect> allEffects = developmentCard.getProdHarvEffects(value);
                    int i = 0;
                    for (ProdHarvEffect effect : allEffects) {      //CArds with auto execute + ask user
                        if (!askUserEffects.contains(effect)) {
                            autoExecute.add(effect);
                        } else {
                            userOptions.add(i);
                        }
                        i++;
                    }
                    askUser.put(developmentCard.getPath(), userOptions);        //askUser: card to ask -> effect to execute from user (only one)
                } else {             //Otherwise if it is allowed I will execute it
                    List<ProdHarvEffect> effects = developmentCard.getProdHarvEffects(value);
                    for (ProdHarvEffect effect : effects) {
                        if (effect.isAllowed(player)) {
                            autoExecute.add(effect);
                        }
                    }
                }
            }
        }

        //Apply the auto-maluses

        List <Effect> temp = new LinkedList<>();
        for (ProdHarvEffect effect : autoExecute)
        {
            for (ProdHarvMalusEffect malusEffect : effect.getMalusEffect()) {
                if (malusEffect.isAllowed(player))
                {
                    temp.add(malusEffect);          //I have to execute the malus because it is not optional
                }
            }
         //   temp.addAll(effect.getMalusEffect());
        }

        ExecuteEffects executeEffects = new ExecuteEffects(temp, player,game);
        if (executeEffects.isAllowed())
        {
            executeEffects.execute();
        }

        //Ask the cards

        if (askUser.size()>0)
        {
            MessageChooseProdHarv messageChooseProdHarv = new MessageChooseProdHarv(askUser);
            GameList.getInstance().setCurrentBlocking(game, this, messageChooseProdHarv);
            //stiamo mandando le carte fra cui scegliere allo stato che poi le manderà al controller e poi al client

            while (userActivateEffect == null)
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
         //   game.getGameStatus().changeState(TransitionType.START_PRODHARV, askUser);
        }

        List<ProdHarvEffect> userProdHarvEffects = new LinkedList<>();

        for (String s : userActivateEffect.keySet()) {
            int userChoice = userActivateEffect.get(s);
            Integer i = askUser.get(s).get(userChoice);       //user choosen option

            DevelopmentCard developmentCard = userAsk.get(s);
            ProdHarvEffect prodHarvEffect = developmentCard.getProdHarvEffects(value).get(i);
            userProdHarvEffects.add(prodHarvEffect);
        }

        //Check if are all allowed

        Player testPlayer = new Player(player);
        for (ProdHarvEffect prodHarvEffect : userProdHarvEffects) {
            for (ProdHarvMalusEffect malusEffect : prodHarvEffect.getMalusEffect()) {
                if (malusEffect.isAllowed(testPlayer))
                {
                    malusEffect.execute(testPlayer, game);
                }
                else
                {
                    //TODO ask again
                }
            }
        }


        //Se ho passato tutti i controlli eseguo le azioni automatiche


        //Apply the auto-bonus
        temp = new LinkedList<>();
        for (ProdHarvEffect effect : autoExecute)
        {
            temp.addAll(effect.getBonusEffect());
        }
        for (ProdHarvEffect userProdHarvEffect : userProdHarvEffects) {
            temp.addAll(userProdHarvEffect.getBonusEffect());
            temp.addAll(userProdHarvEffect.getMalusEffect());
        }
        executeEffects = new ExecuteEffects(temp, player,game);
        if (executeEffects.isAllowed())
        {
            executeEffects.execute();
        }
    }


    @Override
    public boolean isAllowed() {
        return true;
    }

    @Override
    public synchronized void setOptionalParams(Object object) {
        this.userActivateEffect = (Map<String, Integer>) object;
        notifyAll();
    }
}
