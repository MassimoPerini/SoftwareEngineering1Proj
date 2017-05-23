package it.polimi.ingsw.GC_06.State;

import it.polimi.ingsw.GC_06.playerTools.*;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/19/17.
 */
public class StatusController {

    private ArrayList<Player> players;
    private Status currentStatus;
    private int currentPlayer, turn, era,numberTurn, numberEra;

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }
}
