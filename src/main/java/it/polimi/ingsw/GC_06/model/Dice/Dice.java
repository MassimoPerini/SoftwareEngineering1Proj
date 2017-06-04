package it.polimi.ingsw.GC_06.model.Dice;

import java.util.Observable;
import java.util.Random;

/**
 * @author massimo
 * Created by massimo on 12/05/17.
 * This is a dice
 */
public class Dice extends Observable {

    private int minPoint, maxPoint; //Inclusive
    private DiceColor color;
    private int points;

    /**
     * Inits a new dice. minPoint and maxPoint are inclusive
     * @param minPoint
     * @param maxPoint
     * @param color
     */
    public Dice(int minPoint, int maxPoint, DiceColor color)
    {
        super();
        this.minPoint=minPoint;
        this.maxPoint=maxPoint;
        this.color = color;
    }

    /**
     * Roll the dice. It generates a new value AND NOTIFY the observers (usually the FamilyMember of the same value)
     */
    public void roll()
    {
        Random random = new Random();
        points = minPoint + random.nextInt((maxPoint-minPoint)+1);
        setChanged();
        notifyObservers(new Integer(points));
    }

    public DiceColor getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }
}
