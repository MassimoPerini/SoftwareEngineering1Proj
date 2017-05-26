package it.polimi.ingsw.GC_06.model.Dice;

import it.polimi.ingsw.GC_06.FamilyMember;

/**
 * Created by massimo on 12/05/17.
 */
public class DiceSet {

    Dice[] dices = new Dice[DiceColor.values().length];

    public DiceSet()
    {
        super();

        for (int i=0;i<dices.length;i++)
            dices[i]=new Dice(DiceColor.values()[i]);
    }

    //TODO: HERE????  OBSERVER DESIGN PATTERN

    
    public FamilyMember[] createFamilyMembers(boolean zeroFamiliar)
    {
        int i=0;

        if (zeroFamiliar)
            i=1;

        FamilyMember[] familyMembers = new FamilyMember[DiceColor.values().length+i];

        for (i=0;i<DiceColor.values().length;i++) {
            familyMembers[i] = new FamilyMember(DiceColor.values()[i]);
            dices[i].addObserver(familyMembers[i]);
        }
        if (zeroFamiliar)
            familyMembers[i]=new FamilyMember();
        return familyMembers;
    }

    public void roll()
    {
        for (Dice dice : dices)
            dice.roll();
    }

}
