package it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 6/4/17.
 */
public class GiveFamilyBonus {

    private FamilyMember newFamilyMember;
    private boolean permanent;
    private boolean ON;

    public GiveFamilyBonus(FamilyMember newFamilyMember) {
        this.newFamilyMember = newFamilyMember;
    }

    public void modify(FamilyMember familyMember){
        familyMember = newFamilyMember;
    }
}
