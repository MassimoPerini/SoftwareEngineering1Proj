package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Action.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;
/**
 * Created by massimo on 13/05/17.
 */
public class Tower implements Component{

    private ArrayList<TowerFloor> floors = new ArrayList<>();

    public Tower( ArrayList<TowerFloor> floors){
    	this.floors = floors;
    }

    //TODO

    @Override
    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember, int index) {

        if (!isAllowed(familyMember, index))
            throw new IllegalStateException();

        return floors.get(index).addFamilyMember(familyMember);

    }

    @Override
    public boolean isAllowed(FamilyMember familyMember, int index) {
        //TODO se non ci sono + di N (da file ecc...) familiari dello stesso giocatore....

        return floors.get(index).isAllowed(familyMember);
    }
}
