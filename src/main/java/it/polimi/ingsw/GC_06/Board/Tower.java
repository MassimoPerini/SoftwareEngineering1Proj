package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Card.Card;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.Card.Requirement;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.ArrayList;
/**
 * Created by massimo on 13/05/17.
 */
public class Tower implements Component{

    private ArrayList<TowerFloor> towerFloors = new ArrayList<>();
    private int maxSamePlayerFamilyMember;
    private int minFamilyMembersMalus;
    private ArrayList<DevelopmentCard> cards;

    public Tower(ArrayList<TowerFloor> floors, int maxSamePlayerFamilyMember, int minFamilyMembersMalus, ResourceSet malusSet){
    	this.towerFloors = floors;
    	this.maxSamePlayerFamilyMember = maxSamePlayerFamilyMember;
    	this.minFamilyMembersMalus = minFamilyMembersMalus;
    }

    @Override
    public void addFamilyMember(FamilyMember familyMember, int index) {

        if (!isAllowed(familyMember, index))
            throw new IllegalStateException();

    }

    public boolean isNoPenalityAllowed(FamilyMember familyMember, int index)
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
                if (familyMember1.getPlayerUserName().equals(familyMember) && !familyMember1.isNeutral())
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
    public ArrayList<Effect> getEffect(int index) {
        return this.towerFloors.get(index).getEffects();
    }

    public void setCards(ArrayList<DevelopmentCard> cards)
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

    public ArrayList<Requirement> getRequirement(int index)
    {
        return this.towerFloors.get(index).getCard().getRequirements();
    }

    public DevelopmentCard pickCard(int index)
    {
        DevelopmentCard c = getCard(index);
        this.towerFloors.get(index).setCard(null);
        return c;
    }

    public DevelopmentCard getCard (int index)
    {
        return this.towerFloors.get(index).getCard();
    }
}
