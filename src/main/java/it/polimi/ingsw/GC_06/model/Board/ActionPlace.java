package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/27/17.
 */
public interface ActionPlace {

    ArrayList<Effect> addFamilyMember(FamilyMember familyMember);
    boolean isAllowed(FamilyMember familyMember);
    ArrayList<Effect> getEffects();
}
