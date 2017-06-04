package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppe on 5/20/17.
 */

/**
 * @deprecated DON'T USE IT
 *
 */
public interface Component {

    void addFamilyMember(FamilyMember familyMember, int index);
    boolean isAllowed(FamilyMember familyMember, int index);
    List<Effect> getEffect(int index);

}
