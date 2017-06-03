package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.*;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/19/17.
 */
public class GameStatus {

    private ArrayList<Player> players;
    private FsmNode currentStatus;
    private int currentPlayer, turn, era,numberTurn, numberEra;



    GameStatus(FsmNode currentStatus)
    {
        super();

        this.currentStatus = currentStatus;


        players = new ArrayList<>();

    }

    boolean isAllowedAddPlayer(String playerID)
    {
        for (Player player: players)
        {
            if (player.getPLAYER_ID().equals(playerID))
            {
                return false;
            }
        }
        return true;
    }

    public void changeState(TransitionType type)
    {
        if (!currentStatus.canConsume(type)){
            throw new IllegalStateException();
        }
        this.currentStatus = currentStatus.consume(type);
        currentStatus.sendNotify();
    }

    public Player getCurrentPlayer()
    {
        return players.get(currentPlayer);
    }

    void addPlayer(Player player)
    {
        this.players.add(player);
    }

    ArrayList<Player> getPlayers() {
        return players;
    }
}
