package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChoosePayment;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 29/05/17.
 */
public class PayCard implements Action, Blocking {

    private final Player player;
    private final ActionType ACTION_TYPE = ActionType.PAYCARDACTION;
    private final PickCard pickCard;
    private final Tower tower;
    private final int floor;
    private final Game game;
    private Integer optionalParams;

    public PayCard(Tower tower, int floor,Player player, Game game)
    {
        super();
        this.player = player;
        this.game = game;
        this.tower = tower;
        this.floor = floor;
        pickCard = new PickCard(player, tower, floor,game);
    }

    private List<Requirement> getRequirements(Player player)
    {
        List<Requirement> satisfiedRequirements = new LinkedList<>();
        /** we must control if the player can afford the card */

        //MODIFICHIAMO QUI LA CARTA
        DevelopmentCard developmentCard = tower.getTowerFloor().get(floor).getCard();

        //    BonusMalusHandler.filter(player,ACTION_TYPE,developmentCard);
        for(Requirement requirement : developmentCard.getRequirements()){
            if(requirement.isSatisfied(player.getResourceSet()))
                satisfiedRequirements.add(requirement);
        }
        return satisfiedRequirements;
    }

    @Override
    public synchronized void execute() {
        //Execute tower penality
        if (tower.shouldThrowPenality(player.getPLAYER_ID())) {
            ResourceSet malusResources = tower.getMalusOnMultipleFamilyMembers();
            player.variateResource(malusResources);
            //TODO INSERIAMO QUA LA CHIAMATA A FILTER CHE CI DIRÀ SE NON DOBBIAMO PAGARE PIÙ
        }

        //Execute actionspace effects
        List<Effect> effects = tower.getTowerFloor().get(floor).getActionPlace().getEffects();
        ExecuteEffects executeEffects = new ExecuteEffects(effects, player,game);
        executeEffects.execute();

        //executing card requirements

        List<Requirement> satisfiedRequirements = this.getRequirements(player);
        if(satisfiedRequirements.size() == 1){
            player.variateResource(satisfiedRequirements.get(0));
        }
        else if (satisfiedRequirements.size()>1) {
            player.variateResource(satisfiedRequirements.get(optionalParams));
        }

        pickCard.execute();
    }

    @Override
    public synchronized boolean isAllowed() {
        Player pClone = new Player(player);     //CLONE (I hope...) TODO

        //Test tower penality BEFORE adding money from the actionspace

        if (tower.shouldThrowPenality(player.getPLAYER_ID())) {
            ResourceSet malusResources = tower.getMalusOnMultipleFamilyMembers();
            try {
                pClone.variateResource(malusResources);
            }
            catch (IllegalArgumentException e)
            {
                //Non posso sottrarre risorse del malus
                return false;
            }
        }

        //Check requirements (add plane and...)
        //Start effect plane!

        //Apply ActionSpace effects to clone
        List<Effect> effects = tower.getTowerFloor().get(floor).getActionPlace().getEffects();

        ExecuteEffects executeEffects = new ExecuteEffects(effects, pClone,game);
        executeEffects.execute();

        //Are the card requirement ok?
        if (!pClone.isAllowedVariate(tower.getTowerFloor().get(floor).getCard().getRequirements()))
        {
            return false;
        }

        // check multiple requirement

        List<Requirement> satisfiedRequirements = this.getRequirements(pClone);

        //Card requirements not success NOT NEEDED
        /*
        if (satisfiedRequirements.size()==0 && tower.getTowerFloor().get(floor).getCard().getRequirements().size()!=0) {
            return false;
        }
        */

        if (satisfiedRequirements.size()>1){
            MessageServer messageServer = new MessageChoosePayment(satisfiedRequirements);
            GameList.getInstance().setCurrentBlocking(game, this, messageServer);
            while (optionalParams==null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (optionalParams!= null && optionalParams==-1)
        {
            return false;
        }

        return pickCard.isAllowed();
    }


    @Override
    public synchronized void setOptionalParams(Object list) {
        optionalParams = (Integer) list;
        notifyAll();
    }

    //Carta pagabile in più modi
    @Override
    public synchronized void userLoggedOut(String user) {
        optionalParams = -1;
        notifyAll();
    }
}
