package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.*;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/19/17.
 */
public class GameStatus implements Status {

    private static GameStatus gameStatus;

    private ArrayList<Player> players;
    private Status currentStatus;
    private int currentPlayer, turn, era,numberTurn, numberEra;



    GameStatus()
    {
        super();
        players = new ArrayList<>();

    }


    public Player getCurrentPlayer()
    {
        return players.get(currentPlayer);
    }

    void addPlayer(Player player)
    {
        this.players.add(player);
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    ArrayList<Player> getPlayers() {
        return players;
    }
}
