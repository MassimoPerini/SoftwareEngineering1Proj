package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.BonusMalusAction;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/27/17.
 */
public class BigActionSpace implements ActionPlace {
    private FamilyMember familyMember;
    private BonusMalusAction bonusMalus; /** dobbiamo cercare di estendere questa cosa*/
    private ArrayList<Effect> effects;
    private int bonusMalusEntity;
    private int threshold;


    @Override
    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember) {

        //bonusMalus.modify(bonusMalusEntity);
        // TODO da cambiare appena scrivo i bonus malus
        if(!isAllowed(familyMember)){
            throw new IllegalStateException();
        }


        this.setFamilyMember(familyMember);
        return effects;
    }

    @Override
    public boolean isAllowed(FamilyMember familyMember) {
        if(familyMember.getValue() >= threshold){
            return true;
        }
        else
            return false;
    }

    @Override
    public ArrayList<Effect> getEffects() {
        return effects;
    }

    private void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }
}
