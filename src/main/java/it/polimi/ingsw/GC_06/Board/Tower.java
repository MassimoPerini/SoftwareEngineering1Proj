package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.ArrayList;
/**
 * Created by massimo on 13/05/17.
 */
public class Tower implements Component{

    private ArrayList<TowerFloor> towerFloors = new ArrayList<>();
    private int maxSamePlayerFamilyMember;
    private int minFamilyMembersMalus;
    private ResourceSet malusSet;

    public Tower(ArrayList<TowerFloor> floors, int maxSamePlayerFamilyMember, int minFamilyMembersMalus, ResourceSet malusSet){
    	this.towerFloors = floors;
    	this.maxSamePlayerFamilyMember = maxSamePlayerFamilyMember;
    	this.minFamilyMembersMalus = minFamilyMembersMalus;
    }

    @Override
    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember, int index) {

        if (!isAllowed(familyMember, index))
            throw new IllegalStateException();

        return towerFloors.get(index).addFamilyMember(familyMember);

    }

    @Override
    public boolean isAllowed(FamilyMember familyMember, int index) {

        int samePlayerFamilyMember = 0;
        int familyMemberCount = 0;

        for (TowerFloor towerFloor : towerFloors)
        {
            for (FamilyMember familyMember1 : towerFloor.getActionPlace().getMembers())     //Se ci sono + familiari per effetto di carte eroe...
            {
                familyMemberCount++;

                if (familyMember1.getPlayerUserName().equals(familyMember) && !familyMember1.isNeutral())
                {
                    samePlayerFamilyMember++;
                }
            }
        }

        if (samePlayerFamilyMember >= maxSamePlayerFamilyMember)
            return false;

        if (familyMemberCount >= minFamilyMembersMalus)
        {
            //TODO torre occupata: + risorse!!!
            //TODO che fare? Aggiungere ai requisiti delle carte
      //      towerFloors.get(index).get
        }

        return towerFloors.get(index).isAllowed(familyMember);
    }

    //TODO remove


    public ArrayList<TowerFloor> getTowerFloors() {
        return towerFloors;
    }
}
