package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.LinkedList;

/**
 * Created by massimo on 13/05/17.
 */
public class MultipleActionPlace extends ActionPlace{
    private LinkedList<FamilyMember> familyMembers;
    private int requiredValue;  //TODO: ????


    public MultipleActionPlace(int requiredValue)
    {
        super();
        this.requiredValue = requiredValue;
        familyMembers =new LinkedList<FamilyMember>();
    }

    @Override
    public void addFamilyMember(FamilyMember familyMember) {
        if (! this.isAllowedFamilyMember(familyMember))
        {
            throw new IllegalStateException();
        }
        this.familyMembers.add(familyMember);
    }

    //TODO CARTA EROE LUDOVICO ARIOSTO: Puoi piazzare i tuoi familiari in spazi azione occupati
    @Override
    public boolean isAllowedFamilyMember(FamilyMember familyMember)
    {
        if (this.requiredValue > familyMember.getValue())
            return false;
        //return super.areAllowedActions();
        return true;
    }
}
