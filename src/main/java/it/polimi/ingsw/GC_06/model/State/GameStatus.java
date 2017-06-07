package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.*;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/19/17.
 */

/**
 * This class is the main status of the game.
 * Contains the current status, the turn of the game and the players order.
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

    public void endTurn()
    {
        //Todo implement
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

    /**
     * Change the state of the game using a TransitionType
     * The new state will notify its observer
     * @param type
     * @param o
     */
    public void changeState(TransitionType type, Object o)
    {
        if (!currentStatus.canConsume(type)){
            throw new IllegalStateException();
        }
        this.currentStatus = currentStatus.consume(type);
        currentStatus.sendNotify(o);
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public StateName getCurrentStatus() { return this.currentStatus.getID(); }


}
