package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppe on 5/20/17.
 */
public class BoardActionOnTower extends BoardAction {

    private Player player;
    private Tower component;
    private int index;
    private ResourceSet malusSet;


    public BoardActionOnTower(Player player, int index, Tower component, FamilyMember familyMember, int value) {
        super(familyMember, value);
    	this.player = player;
        this.index = index;
        this.component = component;
    }

    @Override
    public void execute() {

        if (!isAllowed())
            throw new IllegalStateException();


        ArrayList<Effect> effects;

        /**if we are in the real action we add the family member in the correct position*/

        if(getFamilyMember()!=null){
            component.addFamilyMember(getFamilyMember(), index);
        }

        executePenality(player.getResourceSet());

        ArrayList<Requirement> satisfiedRequirements = new ArrayList<>();
        List<Requirement> requirements =  component.getRequirement(index);

        effects = component.getEffect(index);
        executeEffects(player, effects);

        /** we must control if the player can afford the card */
        for(Requirement requirement : requirements){
            if(requirement.isSatisfied(player.getResourceSet()))
                satisfiedRequirements.add(requirement);
        }

        if(satisfiedRequirements.size() == 1){
                satisfiedRequirements.get(0).doIt(player.getResourceSet());
        }
        else{
                //TODO sono cazzi amari
        }

        /**we are adding the card to the player board*/
        DevelopmentCard c = component.pickCard(index);
        player.getPlayerBoard().addCard(c);

        executeEffects(player, c.getEffects());

    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean isAllowed() {

        /** è permessa solo quando non c'è un familiare sulla torre*/


        //Check posso piazzare il familiare (o virtuale)
        if (!component.isAllowed(getFamilyMember(), index))
            return false;

        //Check board space

        player.getPlayerBoard().canAdd(component.getCard(index));

        //check prezzo

        Player p = new Player(player);     //CLONE (I hope...) TODO

        if (!executePenality(p.getResourceSet()))
            return false;
        //Check requirements (add plane and...)
        //Start effect plane!

        ArrayList<Effect> effects = component.getEffect(index);
        executeEffects(p, effects);

        List<Requirement> requirements =  component.getRequirement(index);
        for(Requirement requirement : requirements){
            if(requirement.isSatisfied(p.getResourceSet())){
                return true;
            }
        }
        return false;

    }

    private void executeEffects(Player p, ArrayList<Effect> effects)
    {
        for (Effect effect:effects)
        {
            effect.execute(p);
        }
    }

    private boolean executePenality(ResourceSet resourceSet)
    {
        if (!component.isNoPenalityAllowed())
        {
            if (!resourceSet.isIncluded(malusSet))
                return false;
            resourceSet.removeResource(malusSet);
        }
        return true;
    }
}
