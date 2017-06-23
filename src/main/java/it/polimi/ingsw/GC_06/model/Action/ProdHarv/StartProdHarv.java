package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChooseProdHarv;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvMalusEffect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 05/06/17.
 */
public class StartProdHarv implements Action, Blocking {

    private final AskUserCard prodHarvFilterCard;
    private int value;
    private final Player player;
    private Game game;
    private Map<String, Integer> userActivateEffect;
    private ActionType actionType;

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
        this.prodHarvFilterCard = askUserCardFilter;
        this.value = value;
        this.player = player;
        this.actionType = actionType;
    }

    /**
     * Starts a production/harvest
     */
    @Override
    public synchronized void execute() {
        game.getGameStatus().changeState(TransitionType.START_PRODHARV);

        List<ProdHarvEffect> autoExecute = new LinkedList<>();
        Map<String, List<ProdHarvEffect>> askUser = new HashMap<>();

        //Select the cards we need to ask
    /**FamilyMember familyMember = new FamilyMember("","");
        familyMember.setValue(value);
        BonusMalusHandler.filter(player,actionType,familyMember);
        value = familyMember.getValue(); */


        for (DevelopmentCard developmentCard: player.getPlayerBoard().getDevelopmentCards())
        {
            List<ProdHarvEffect> askUserEffects = prodHarvFilterCard.askUser(developmentCard, value, player);
            if (askUserEffects.size()>0)     //I need to ask
            {
                List<ProdHarvEffect> allEffects = developmentCard.getProdHarvEffects(value);
                for (ProdHarvEffect effect : allEffects) {      //CArds with auto execute + ask user
                    if (!askUserEffects.contains(effect))
                    {
                        autoExecute.add(effect);
                    }
                }
                askUser.put(developmentCard.getPath(), askUserEffects);
            }
            else {             //Otherwise if it is allowed I will execute it
                List<ProdHarvEffect> effects = developmentCard.getProdHarvEffects(value);
                for (ProdHarvEffect effect : effects) {
                    if (effect.isAllowed(player)){
                        autoExecute.add(effect);
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
            int elem = userActivateEffect.get(s);
            ProdHarvEffect prodHarvEffect = askUser.get(s).get(elem);
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
    }
}
