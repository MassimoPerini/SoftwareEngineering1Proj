package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

/**
 * Created by giuseppe on 5/20/17.
 */
public interface Component {

    public void addFamilyMember(FamilyMember familyMember, int index);
    public void isAllowed(FamilyMember familyMember, int index );
}
