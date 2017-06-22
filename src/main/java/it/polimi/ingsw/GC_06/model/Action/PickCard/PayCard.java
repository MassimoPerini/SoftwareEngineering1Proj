package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessageChoosePayment;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.Blocking;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 29/05/17.
 */
public class PayCard implements Action, Blocking, Runnable {

    private final Player player;
    private final ActionType ACTION_TYPE = ActionType.PAYCARDACTION;
    private final PickCard pickCard;
    private final Tower tower;
    private final int floor;
    private final Game game;
    private List optionalParams;

    public PayCard(Tower tower, int floor,Player player, Game game)
    {
        super();
        this.player = player;
        this.game = game;
        this.tower = tower;
        this.floor = floor;
        pickCard = new PickCard(player, tower, floor,game);
    }

    private List<Requirement> getRequirements()
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
    public void execute() {

        List<Requirement> satisfiedRequirements = this.getRequirements();
        if(satisfiedRequirements.size() == 1){
            satisfiedRequirements.get(0).doIt(player);
        }
        else if (satisfiedRequirements.size()>1){
            satisfiedRequirements.get((Integer) optionalParams.get(0)).doIt(player);
        }
        game.getGameStatus().changeState(TransitionType.PAY_CARD);
        pickCard.execute();
    }

    @Override
    public synchronized boolean isAllowed() {
        Player pClone = new Player(player);     //CLONE (I hope...) TODO

        //Test tower penality BEFORE adding money from the actionspace

        if (!tower.isNoPenalityAllowed()) {
            ResourceSet malusResources = tower.getMalusOnMultipleFamilyMembers();
            try {
                pClone.variateResource(malusResources);
            }
            catch (IllegalArgumentException e)
            {
                //Non posso sottrarre risorse
                return false;
            }
        }

        //Check requirements (add plane and...)
        //Start effect plane!

        //Apply ActionSpace effects to clone
        List<Effect> effects = tower.getTowerFloor().get(floor).getActionPlace().getEffects();

        for(Effect effect: effects)
        {
            effect.execute(pClone,game);
        }

        if (!tower.getTowerFloor().get(floor).getCard().isSatisfied(player.getResourceSet()))
        {
            return false;
        }

        // check optional value

        List<Requirement> satisfiedRequirements = this.getRequirements();

        //Card requirements not success
        if (satisfiedRequirements.size()==0 && tower.getTowerFloor().get(floor).getCard().getRequirements().size()!=0) {
            return false;
        }

        if (satisfiedRequirements.size()>1){
            GameList.getInstance().setCurrentBlocking(game, this);
            MessageServer messageServer = new MessageChoosePayment(satisfiedRequirements);
            game.getGameStatus().sendMessage(messageServer);
            while (optionalParams==null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return pickCard.isAllowed();
    }


    @Override
    public void run() {
        if (isAllowed())
            execute();
    }

    @Override
    public synchronized void setOptionalParams(List list) {
        optionalParams = list;
        notifyAll();
    }
}
