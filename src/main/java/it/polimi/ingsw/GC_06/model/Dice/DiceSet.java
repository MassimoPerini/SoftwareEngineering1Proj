package it.polimi.ingsw.GC_06.model.Dice;

import it.polimi.ingsw.GC_06.FamilyMember;

/**
 * Created by massimo on 12/05/17.
 */
public class DiceSet {

    private Dice[] dices = new Dice[DiceColor.values().length];

    public DiceSet()
    {
        super();

        for (int i=0;i<dices.length;i++)
            dices[i]=new Dice(DiceColor.values()[i]);
    }

    //TODO: HERE????  OBSERVER DESIGN PATTERN


    public Dice[] getDices() {
        return dices;
    }

    public void roll()
    {
        for (Dice dice : dices)
            dice.roll();
    }

}
