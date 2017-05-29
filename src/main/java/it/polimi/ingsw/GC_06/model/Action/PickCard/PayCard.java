package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 29/05/17.
 */
public class PayCard implements Action {

    private Player player;
    private DevelopmentCard developmentCard;

    public PayCard(DevelopmentCard developmentCard, Player player)
    {
        this.player = player;
        this.developmentCard = developmentCard;
    }

    @Override
    public void execute() {

        if (!isAllowed())
            throw new IllegalStateException();

        List<Requirement> satisfiedRequirements = new LinkedList<>();
        /** we must control if the player can afford the card */
        for(Requirement requirement : developmentCard.getRequirements()){
            if(requirement.isSatisfied(player.getResourceSet()))
                satisfiedRequirements.add(requirement);
        }

        if(satisfiedRequirements.size() == 1){
            satisfiedRequirements.get(0).doIt(player);
            this.executeEffects(player, developmentCard.getEffects());
        }
        else{
            //TODO CAMBIO STATO / notifica alla view
        }


    }

    @Override
    public boolean isAllowed() {
        for(Requirement requirement : developmentCard.getRequirements()){
            if(requirement.isSatisfied(player.getResourceSet()));
                return true;
        }
        return false;
    }

    private void executeEffects(Player p, List<Effect> effects)
    {
        for (Effect effect:effects)
            effect.execute(p);
    }

}
