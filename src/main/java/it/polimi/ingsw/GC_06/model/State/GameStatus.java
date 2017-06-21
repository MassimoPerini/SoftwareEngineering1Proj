package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by giuseppe on 5/19/17.
 */

/**
 * This class is the main status of the game.
 * Contains the current status, the turn of the game and the players order.
 */
public class GameStatus extends Observable {

    private FsmNode currentStatus;
    private final Map<String, Player> players;
    private final int  maxPlayers, minPlayers;

    private static final String MINPLAYERSKEY = "min_players";
    private static final String MAXPLAYERSKEY = "max_players";


    GameStatus(FsmNode currentStatus) throws IOException {
        super();
        this.currentStatus = currentStatus;
        maxPlayers = Integer.parseInt(Setting.getInstance().getProperty(MAXPLAYERSKEY));
        minPlayers = Integer.parseInt(Setting.getInstance().getProperty(MINPLAYERSKEY));

        if (minPlayers<1)
            throw new IllegalArgumentException();

        players = new HashMap<>();

    }


    /**
     * Add a new Player to the match
     * @param player
     * @throws IllegalStateException
     */
    void addPlayer (Player player) throws IllegalStateException, IllegalArgumentException
    {
        if (player == null)
            throw new NullPointerException();
        if (this.getPlayers().size() >= maxPlayers)
            throw new IllegalStateException("too many players");

        if (this.players.get(player.getPLAYER_ID()) != null)
            throw new IllegalArgumentException("username already present");

        this.players.put(player.getPLAYER_ID(), player);
    }

    void start() {
        if (players.size() < minPlayers)
            throw new IllegalArgumentException();
    }

    /**
     * Change the state of the game using a TransitionType
     * The new state will notify its observer
     * @param type
     * @param o
     */

    public void sendMessage(MessageServer serverMessage)
    {
        setChanged();
        notifyObservers(serverMessage);
    }

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

    public Map<String, Player> getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public FsmNode getCurrentStatus() {return currentStatus;}
}
