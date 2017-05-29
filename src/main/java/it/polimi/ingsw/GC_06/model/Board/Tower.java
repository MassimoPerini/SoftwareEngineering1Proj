package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massimo on 13/05/17.
 */
public class Tower implements Component{

    private List<TowerFloor> towerFloors;
    private int maxSamePlayerFamilyMember;
    private int minFamilyMembersMalus;
    private List<DevelopmentCard> cards;
    private ResourceSet malusSet;

    public Tower(List<TowerFloor> floors, int maxSamePlayerFamilyMember, int minFamilyMembersMalus){
    	this.towerFloors = floors;
    	this.maxSamePlayerFamilyMember = maxSamePlayerFamilyMember;
    	this.minFamilyMembersMalus = minFamilyMembersMalus;
        this.malusSet = new ResourceSet();
        this.malusSet.variateResource(Resource.MONEY, -3);
    }

    @Override
    public void addFamilyMember(FamilyMember familyMember, int index) {

        if (!isAllowed(familyMember, index))
            throw new IllegalStateException();
        this.towerFloors.get(index).addFamilyMember(familyMember);

    }

    public boolean isNoPenalityAllowed()
    {
        int familyMemberCount = 0;
        for (TowerFloor towerFloor : towerFloors)
        {
            for (FamilyMember familyMember1 : towerFloor.getActionPlace().getMembers()) {     //Se ci sono + familiari per effetto di carte eroe...
                familyMemberCount++;
            }
        }
        return familyMemberCount < minFamilyMembersMalus;

    }

    @Override
    public boolean isAllowed(FamilyMember familyMember, int index) {

        int samePlayerFamilyMember = 0;

        for (TowerFloor towerFloor : towerFloors)
        {
            for (FamilyMember familyMember1 : towerFloor.getActionPlace().getMembers())     //Se ci sono + familiari per effetto di carte eroe...
            {
                if (familyMember1.getPlayerUserName().equals(familyMember.getPlayerUserName()) && !familyMember1.isNeutral())
                {
                    samePlayerFamilyMember++;
                }
            }
        }

        if (samePlayerFamilyMember >= maxSamePlayerFamilyMember)
            return false;
        return towerFloors.get(index).isAllowed(familyMember);
    }

    @Override
    public List<Effect> getEffect(int index) {
        return this.towerFloors.get(index).getEffects();
    }

    public void setCards(List<DevelopmentCard> cards)
    {
        this.cards = cards;
    }

    public void shuffle()
    {
        for (int i=0;i<this.towerFloors.size();i++)
        {
            this.towerFloors.get(i).setCard(cards.get(0));
            cards.remove(0);
        }
    }

    public List<Requirement> getRequirement(int index)
    {
        return this.towerFloors.get(index).getCard().getRequirements();
    }

    public DevelopmentCard getCard (int index)
    {
        return this.towerFloors.get(index).getCard();
    }

    public TowerFloor getTowerFloor(int index)
    {
        return this.towerFloors.get(index);
    }

    public ResourceSet getMalusSet() {
        return malusSet;
    }
}
