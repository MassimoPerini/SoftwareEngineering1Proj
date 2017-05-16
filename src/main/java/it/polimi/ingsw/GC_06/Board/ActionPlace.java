package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;


/**
 * Created by massimo on 13/05/17.
 */
public abstract class ActionPlace {

    public abstract void addFamilyMember(FamilyMember familyMember);
    public abstract boolean isAllowedFamilyMember(FamilyMember familyMember);

}
