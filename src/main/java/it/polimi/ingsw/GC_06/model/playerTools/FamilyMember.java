package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageFamilyMember;
import it.polimi.ingsw.GC_06.model.Dice.DiceColor;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 12/05/17.
 */
public class FamilyMember extends Observable implements Observer {

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

    public String getDiceColor() {
        return diceColor;
    }

    public boolean isNeutral()
    {
        return diceColor.equals("");
    }

    public void update(Observable obs, Object args)
    {
        Integer val =(Integer) args;
        setValue(val.intValue());
    }


    public int getValue() {
        return value;
    }

    public void variateValue(int variate)
    {
        this.value+=variate;
    }

    public void setValue(int value) {

        this.value = value;

        MessageServer messageServer = new MessageFamilyMember(value, diceColor, playerUserName);

        setChanged();
        notifyObservers(messageServer);
    }
}
