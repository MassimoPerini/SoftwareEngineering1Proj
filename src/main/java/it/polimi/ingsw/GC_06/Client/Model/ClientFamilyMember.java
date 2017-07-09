package it.polimi.ingsw.GC_06.Client.Model;

import java.io.Serializable;

/**
 * Created by giuseppe on 6/14/17.
 * this class represents a familyMember client side, is a serializable class
 */
public class ClientFamilyMember implements Serializable {
    private String player;
    private int value;
    private String color;
    private String playerColor;

    public ClientFamilyMember(String player, int value, String color) {
        this.player = player;
        this.value = value;
        this.color = color;
    }

    public synchronized String getColor() {
        return color;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }


    //*******


    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public synchronized String getPlayer() {
        return player;
    }

    public synchronized int getValue() {
        return value;
    }

    public String getPlayerColor() {
        return playerColor;
    }
}
