package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Dice.DiceColor;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 12/05/17.
 */
public class FamilyMember implements Observer {

    private PlayerId playerColor;
    private int value;
    private DiceColor diceColor;
    private String playerUserName;

    public String getPlayerUserName() {
        return playerUserName;
    }

    public FamilyMember(){
        this(null, null);
    }

    public FamilyMember(DiceColor color)
    {
        this(null, color);
    }

    public FamilyMember(PlayerId playerColor, DiceColor diceColor)
    {
        super();
        this.diceColor=diceColor;
        this.playerColor=playerColor;
    }

    public boolean isNeutral()
    {
        return diceColor == null;
    }

    public void update(Observable obs, Object args)
    {
        Integer val =(Integer) args;
        this.value = val.intValue();
    }

    public void setPlayerColor(PlayerId playerColor) {
        this.playerColor = playerColor;
    }

    public int getValue() {
        return value;
    }
    
    public PlayerId getColour() {
    	return this.playerColor;
    }
}
