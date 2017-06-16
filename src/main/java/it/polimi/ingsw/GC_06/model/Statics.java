package it.polimi.ingsw.GC_06.model;

/**
 * Created by giuseppe on 6/16/17.
 */
public class Statics {

    private String playerID; /** username*/
    private int numberOfVictories;
    private int numberOfDefeats;
    private int timePlayed;

    public Statics(String playerID) {
        this.playerID = playerID;
        numberOfVictories = 0;
        numberOfDefeats = 0;
        timePlayed = 0;
    }



}
