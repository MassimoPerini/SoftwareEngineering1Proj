package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.Collections;
import java.util.List;

/**
 * Created by massimo on 13/05/17.
 */

/**
 * @author massimo
 * This class represents a "tower" of the game
 */
public class Tower{

    private final List<TowerFloor> towerFloors;
    private int maxSamePlayerFamilyMember;
    private int minFamilyMembersMalus;
    private List<DevelopmentCard> cards;
    private ResourceSet malusOnMultipleFamilyMembers;
    private String color;

    public Tower(List<TowerFloor> floors, int maxSamePlayerFamilyMember, int minFamilyMembersMalus, String color, ResourceSet malusOnMultipleFamilyMembers){
    	this.towerFloors = floors;
    	this.maxSamePlayerFamilyMember = maxSamePlayerFamilyMember;
    	this.minFamilyMembersMalus = minFamilyMembersMalus;
    	this.color = color;
    	this.malusOnMultipleFamilyMembers = malusOnMultipleFamilyMembers;
    }

    /**
     * Chack if you deserve a malus when you add the card (the -3 gold).
     * It will be used together with getMalusOnMultipleFamilyMembers
     * TODO DO IT!
     * @return
     */
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

    /**
     * Check if the player can add the family member to a floor. It doesn't manage the malus when a player is added
     * @param familyMember
     * @param towerFloorUser
     * @return
     */
    public boolean isAllowed(FamilyMember familyMember, TowerFloor towerFloorUser) {

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
        return towerFloorUser.isAllowed(familyMember);
    }

    /**
     * Set an amount of cards on the tower
     * @param cards
     */
    public void setCards(List<DevelopmentCard> cards)
    {
        this.cards = cards;
    }

    /**
     * @requires this.cards.size() >= towerFloors.size()
     * Gives a new card for each towerFloor
     */
    public void shuffle()
    {
        for (int i=0;i<this.towerFloors.size();i++)
        {
            this.towerFloors.get(i).setCard(cards.get(0));
            cards.remove(0);
        }
    }

    public List<TowerFloor> getTowerFloor()
    {
        return Collections.unmodifiableList(this.towerFloors);
    }

    public ResourceSet getMalusOnMultipleFamilyMembers() {
        return malusOnMultipleFamilyMembers;
    }

    public String getColor() {
        return color;
    }
}
