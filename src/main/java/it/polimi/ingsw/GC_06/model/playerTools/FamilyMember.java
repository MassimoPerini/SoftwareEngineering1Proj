package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.UpdateValueFamilyMember;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 12/05/17.
 */
public class FamilyMember extends Observable implements Observer {

    private int value;
    private String diceColor;
    private String playerUserName;
    private boolean alreadyUsed;

    public String getPlayerUserName() {
        return playerUserName;
    }


    public FamilyMember(String diceColor, String playerUserName)
    {
        super();
        this.diceColor=diceColor;
        this.playerUserName = playerUserName;
        alreadyUsed = false;
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
        this.alreadyUsed = false;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {

        this.value = value;

        MessageServer messageServer = new UpdateValueFamilyMember(value, diceColor, playerUserName);

        setChanged();
        notifyObservers(messageServer);
    }

    public boolean isAllowed()
    {
        return !alreadyUsed;
    }

    public void useIt()
    {
        alreadyUsed = true;
    }
}
