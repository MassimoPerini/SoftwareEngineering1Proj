package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public interface Component {

    void addFamilyMember(FamilyMember familyMember, int index);
    boolean isAllowed(FamilyMember familyMember, int index);
    ArrayList<Effect> getEffect(int index);

}
