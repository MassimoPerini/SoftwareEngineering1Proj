package it.polimi.ingsw.GC_06.model.Action.EndGame;

import java.io.Serializable;

/**
 * Created by giuseppe on 7/3/17.
 * Contiene i dati legati ad un player
 */
public class PersonalStatistics implements Serializable {

    private String playerID;
    private int faithPointQuantity;
    private int militaryPointQuantity;
    private int victoryPointQuantity;
    private int totalResourceAmountQuantity;


    public PersonalStatistics(String playerID, int faithPointQuantity, int militaryPointQuantity, int victoryPointQuantity, int totalResourceAmountQuantity) {
        this.playerID = playerID;
        this.faithPointQuantity = faithPointQuantity;
        this.militaryPointQuantity = militaryPointQuantity;
        this.victoryPointQuantity = victoryPointQuantity;
        this.totalResourceAmountQuantity = totalResourceAmountQuantity;
    }
}
