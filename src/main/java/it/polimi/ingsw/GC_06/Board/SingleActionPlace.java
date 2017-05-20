package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

/**
 * Created by massimo on 13/05/17.
 */
public class SingleActionPlace extends ActionPlace {

    private FamilyMember familyMember;
    private int requiredValue;

    public SingleActionPlace(int requiredValue)
    {
        super();
        this.requiredValue = requiredValue;
        familyMember = null;
    }

    //TODO FIX HERE

    @Override
    public void addFamilyMember(FamilyMember familyMember) {
        if (! this.isAllowedFamilyMember(familyMember))
        {
            throw new IllegalStateException();
        }
        this.familyMember = familyMember;
    }

    //TODO CARTA EROE LUDOVICO ARIOSTO: Puoi piazzare i tuoi familiari in spazi azione occupati. ANCHE PER VALORE!
    @Override
    public boolean isAllowedFamilyMember(FamilyMember familyMember)
    {
        if (familyMember!=null)
            return false;
        return this.requiredValue <= familyMember.getValue();
    }
}
