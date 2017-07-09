package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.UpdateValueFamilyMember;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 12/05/17.
 * This class represents a familyMember
 */
public class FamilyMember extends Observable implements Observer {

    private int value;
    private String diceColor;
    private String playerUserName;
    private boolean alreadyUsed;

    /**
     *
     * @return returns player's username
     */
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

    /**
     *
     * @return return true if the familymember is the neutral one
     */
    public boolean isNeutral()
    {
        return diceColor.equals("");
    }

    /**
     *
     * @param obs
     * @param args
     */
    public void update(Observable obs, Object args)
    {
        Integer val =(Integer) args;
        setValue(val.intValue());
        this.alreadyUsed = false;
    }


    public int getValue() {
        return value;
    }

    /**
     * this method assigns an arbitrary value to the familyMember
     * @param value the value to assign to the familymember
     */
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

    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }
}
