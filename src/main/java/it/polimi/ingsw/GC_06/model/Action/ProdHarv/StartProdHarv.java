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
import it.polimi.ingsw.GC_06.model.PersonalBonusTile;
import it.polimi.ingsw.GC_06.model.State.Game;
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

    private final AskUserCard askUser;
    private int value;
    private final Player player;
    private final Game game;
    private Map<String, Integer> userActivateEffect;
    private ActionType actionType;
    private ProdHarvFilterCard prodHarvFilterCard;
    private  Map<String, DevelopmentCard> cardMap = new HashMap<>();
    private Map<String, List<Integer>> cardToAskUser = new HashMap<>();
    private String familyMemberColour;

    /**
     *
     * @param askUserCardFilter The functions that choose the cards we need to ask
     * @param value The value of the production/harvest
     * @param player The player that started the Action
     */

    public StartProdHarv(ActionType actionType, AskUserCard askUserCardFilter, int value, Player player, Game game)   //It should check if there is at least a "resource transformation" effect
    {
        super();
        if (askUserCardFilter==null)
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
    public synchronized void execute() throws InterruptedException {
     //   game.getGameStatus().changeState(TransitionType.START_PRODHARV);

        List<ProdHarvEffect> autoExecute = new LinkedList<>();


        FamilyMember fakeFamilyMember = new FamilyMember(familyMemberColour,"");
        fakeFamilyMember.setValue(value);

        BonusMalusHandler.filter(player,actionType,fakeFamilyMember);

        value = fakeFamilyMember.getValue();

        // se il bonus avviene una volta per turno lo elimino una volta iniziata l'azione
        player.getBonusMalusSet().removeBonusMalusAction(actionType,null);


        for (DevelopmentCard developmentCard: player.getPlayerBoard().getDevelopmentCards())
        {
            if (prodHarvFilterCard.isSatisfiable(developmentCard)) {    //If is Yellow/Green
                List<ProdHarvEffect> askUserEffects = this.askUser.askUser(developmentCard, value, player); //The effects of the card I should ask
                List<Integer> userOptions = new LinkedList<>();

                if (askUserEffects.size() > 0)     //I need to ask at least one effect
                {
                    cardMap.put(developmentCard.getPath(), developmentCard);        //name card -> developmentCard
                    List<ProdHarvEffect> allEffects = developmentCard.getProdHarvEffects(value);
                    int i = 0;
                    for (ProdHarvEffect effect : allEffects) {      //CArds with auto execute + ask user
                        if (effect.isAllowed(player)) {
                            if (!askUserEffects.contains(effect)) {     //The allowed effects that are not in the "ask user" list are auto-execute
                                autoExecute.add(effect);
                            } else {
                                userOptions.add(i);     //i is the index of the "total effect" on the card (ex. 0 is automatic, 1 is user-enabled, so it saves 1)
                            }
                        }
                        i++;
                    }
                    cardToAskUser.put(developmentCard.getPath(), userOptions);        //askUser: card to ask -> effect to execute from user (only one)
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


        //Eseguo il personal malus scelto

        List<Effect> personalBonusEffects = new LinkedList<>();

        for (PersonalBonusTile personalBonusTile : player.getPersonalBonus()) {
            List <ProdHarvEffect> bonus = personalBonusTile.getBonus(actionType, value);

            for (ProdHarvEffect persProdHarvEff : bonus) {
                for (ProdHarvMalusEffect prodHarvMalusEffect : persProdHarvEff.getMalusEffect()) {
                    if (prodHarvMalusEffect.isAllowed(player))
                    {
                        prodHarvMalusEffect.execute(player, game);
                    }
                }
                personalBonusEffects.addAll(persProdHarvEff.getBonusEffect());
            }
        }

        List <Effect> temp = new LinkedList<>();
        for (ProdHarvEffect effect : autoExecute)
        {
            for (ProdHarvMalusEffect malusEffect : effect.getMalusEffect()) {
                temp.add(malusEffect);          //I have to execute the malus because it is not optional
            }
         //   temp.addAll(effect.getMalusEffect());
        }

        ExecuteEffects executeEffects = new ExecuteEffects(temp, player,game);
        if (executeEffects.isAllowed())
        {
            executeEffects.execute();
        }

        //Ask the cards
        List<ProdHarvEffect> userProdHarvEffects = new LinkedList<>();


        while (cardToAskUser.size()>0 && userActivateEffect == null) {
            MessageChooseProdHarv messageChooseProdHarv = new MessageChooseProdHarv(cardToAskUser);
            GameList.getInstance().setCurrentBlocking(game, this, messageChooseProdHarv);

            while (userActivateEffect == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new InterruptedException();
                }
            }
            //   game.getGameStatus().changeState(TransitionType.START_PRODHARV, askUser);



            for (String s : userActivateEffect.keySet()) {
                int userChoice = userActivateEffect.get(s);     //user: for xyz I want the 2 choice. What is the real index?
                if (userChoice==-1)
                {
                    continue;
                }
                Integer i = cardToAskUser.get(s).get(userChoice);       //I get the real effect index

                DevelopmentCard developmentCard = cardMap.get(s);
                ProdHarvEffect prodHarvEffect = developmentCard.getProdHarvEffects(value).get(i);   //get the user effect
                userProdHarvEffects.add(prodHarvEffect);
            }

            //Check if are all allowed

            Player testPlayer = new Player(player);
            for (ProdHarvEffect prodHarvEffect : userProdHarvEffects) {
                for (ProdHarvMalusEffect malusEffect : prodHarvEffect.getMalusEffect()) {
                    if (malusEffect.isAllowed(testPlayer)) {
                        try {
                            malusEffect.execute(testPlayer, game);      //executing all the malus the user choosen
                        } catch (InterruptedException e) {
                            return;
                        }
                    } else {
                        //ask again, user input not valid
                        userActivateEffect = null;
                        break;
                    }
                }
                if (userActivateEffect == null) {
                    break;
                }
            }

        }

        //Checks OK, execute all


        //Apply the auto-bonus
        temp = new LinkedList<>();
        if (personalBonusEffects!=null) {
            temp.addAll(personalBonusEffects);
        }
        for (ProdHarvEffect effect : autoExecute)
        {
            temp.addAll(effect.getBonusEffect());       //malus effects already applied
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

    @Override
    public synchronized void userLoggedOut(String user) {
        this.userActivateEffect = new HashMap<>();
        for (String card : cardToAskUser.keySet()) {
            this.userActivateEffect.put(card, -1);
        }
        notifyAll();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setFamilyMember(String familyMemberColour) {
        this.familyMemberColour = familyMemberColour;
    }
}
