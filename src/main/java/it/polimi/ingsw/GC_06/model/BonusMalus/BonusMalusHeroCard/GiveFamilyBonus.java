package it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

/**
 * Created by giuseppe on 6/4/17.
 */
public class GiveFamilyBonus {

    /** questo bonus è una cagata spero di riuscire ad eliminarlo*/
    private FamilyMember newFamilyMember;

    public GiveFamilyBonus(FamilyMember newFamilyMember) {
        this.newFamilyMember = newFamilyMember;
    }

    public void modify(FamilyMember familyMember){
        familyMember = newFamilyMember;
    }
}
