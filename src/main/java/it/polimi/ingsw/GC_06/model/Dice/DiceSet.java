package it.polimi.ingsw.GC_06.model.Dice;

/**
 * Created by massimo on 12/05/17.
 * @author massimo
 * This class is the set of dices
 */
public class DiceSet {

    private Dice[] dices = new Dice[DiceColor.values().length];

    public DiceSet()
    {
        super();

    }

    public Dice[] getDices() {
        return dices;
    }

    public void roll()
    {
        for (Dice dice : dices)
            dice.roll();
    }

}
