package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.model.Dice.DiceColor;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 12/05/17.
 */
public class FamilyMember implements Observer {

    private int value;
    private String diceColor;
    private String playerUserName;

    public String getPlayerUserName() {
        return playerUserName;
    }


    public FamilyMember(String diceColor, String playerUserName)
    {
        super();
        this.diceColor=diceColor;
        this.playerUserName = playerUserName;
    }

    public boolean isNeutral()
    {
        return diceColor.equals("");
    }

    public void update(Observable obs, Object args)
    {
        Integer val =(Integer) args;
        this.value = val.intValue();
    }


    public int getValue() {
        return value;
    }

    public void variateValue(int variate)
    {
        this.value+=variate;
    }
    
}
