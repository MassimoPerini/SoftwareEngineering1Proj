package it.polimi.ingsw.GC_06;

import java.util.Observable;
import java.util.Random;

/**
 * Created by massimo on 12/05/17.
 */
public class Dice extends Observable {

    private int points;
    private Random random;
    private int minPoint, maxPoint; //Inclusive
    private DiceColor color;

    public Dice (DiceColor color)
    {
        this(0, 6, color);
    }

    public Dice(int minPoint, int maxPoint, DiceColor color)
    {
        super();
        this.random = new Random();
        this.minPoint=minPoint;
        this.maxPoint=maxPoint;
        this.color = color;
    }

    public void roll()
    {
        points = minPoint + random.nextInt((maxPoint-minPoint)+1);
        setChanged();
        notifyObservers(new Integer(points));
    }

    public int getPoints() {
        return points;
    }
}
