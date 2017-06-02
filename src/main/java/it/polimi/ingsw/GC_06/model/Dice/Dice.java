package it.polimi.ingsw.GC_06.model.Dice;

import java.util.Observable;
import java.util.Random;

/**
 * Created by massimo on 12/05/17.
 */
public class Dice extends Observable {

    private int minPoint, maxPoint; //Inclusive
    private DiceColor color;
    private int points;

    public Dice (DiceColor color)
    {
        this(0, 6, color);
    }

    public Dice(int minPoint, int maxPoint, DiceColor color)
    {
        super();
        this.minPoint=minPoint;
        this.maxPoint=maxPoint;
        this.color = color;
    }

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
