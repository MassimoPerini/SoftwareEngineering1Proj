package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Action.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public interface Component {

    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember);
    public boolean isAllowed(FamilyMember familyMember, int index);
}
