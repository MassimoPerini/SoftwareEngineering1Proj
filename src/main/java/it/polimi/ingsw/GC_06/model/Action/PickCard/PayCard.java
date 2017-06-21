package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
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
public class PayCard implements Action {

    private final Player player;
    private final ActionType ACTION_TYPE = ActionType.PAYCARDACTION;
    private final PickCard pickCard;
    private final Tower tower;
    private final int floor;
    private final Game game;

    public PayCard(Tower tower, int floor,Player player, Game game)
    {
        super();
        this.player = player;
        this.game = game;
        this.tower = tower;
        this.floor = floor;
        pickCard = new PickCard(player, tower, floor,game);
    }

    @Override
    public void execute() {


        List<Requirement> satisfiedRequirements = new LinkedList<>();
        /** we must control if the player can afford the card */

        //MODIFICHIAMO QUI LA CARTA
        DevelopmentCard developmentCard = tower.getTowerFloor().get(floor).getCard();

    //    BonusMalusHandler.filter(player,ACTION_TYPE,developmentCard);
        for(Requirement requirement : developmentCard.getRequirements()){
            if(requirement.isSatisfied(player.getResourceSet()))
                satisfiedRequirements.add(requirement);
        }

        if (satisfiedRequirements.size()>1){
            game.getGameStatus().changeState(TransitionType.ASK_PAYMENT, satisfiedRequirements);
            return;
        }

        else if(satisfiedRequirements.size() == 1){
            satisfiedRequirements.get(0).doIt(player);
        }
        game.getGameStatus().changeState(TransitionType.PAY_CARD);
        pickCard.execute();
    }

    @Override
    public boolean isAllowed() {

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

        return pickCard.isAllowed();
    }



}
