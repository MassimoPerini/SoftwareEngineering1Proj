package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
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
    private final DevelopmentCard developmentCard;
    private final ActionType ACTION_TYPE = ActionType.PAYCARDACTION;
    private Game game;

    public PayCard(DevelopmentCard developmentCard, Player player,Game game)
    {
        super();
        this.player = player;
        this.developmentCard = developmentCard;
        this.game = game;
    }

    @Override
    public void execute() {

        if (!isAllowed())
            throw new IllegalStateException();

        game.getGameStatus().changeState(TransitionType.PAY_CARD);

        List<Requirement> satisfiedRequirements = new LinkedList<>();
        /** we must control if the player can afford the card */

        //MODIFICHIAMO QUI LA CARTA

        BonusMalusHandler.filter(player,ACTION_TYPE,developmentCard);

        for(Requirement requirement : developmentCard.getRequirements()){
            if(requirement.isSatisfied(player.getResourceSet()))
                satisfiedRequirements.add(requirement);
        }

        if(satisfiedRequirements.size() == 1){
            satisfiedRequirements.get(0).doIt(player);
        }
        else{
            game.getGameStatus().changeState(TransitionType.ASK_PAYMENT, satisfiedRequirements);
        }
    }

    @Override
    public boolean isAllowed() {
        return developmentCard.isSatisfied(player.getResourceSet());
    }



}
