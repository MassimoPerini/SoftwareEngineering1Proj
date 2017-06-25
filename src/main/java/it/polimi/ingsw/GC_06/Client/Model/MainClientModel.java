package it.polimi.ingsw.GC_06.Client.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by giuseppe on 6/14/17.
 */
public class MainClientModel extends Observable{
    private final Map<String, ClientPlayerBoard> clientPlayerBoard;
    private final ClientBoardGame clientBoardGame;
    private final PlayerBonusActions playerBonusActions;
    private ClientStateName myStatus;
    private ClientStateName allStatus;
    private ClientStateName popups;
    private int era, turn;
    private String currentPlayer;
    private String myUsername;


    public MainClientModel()
    {
        playerBonusActions = new PlayerBonusActions();
        clientPlayerBoard = new HashMap<>();
        this.clientBoardGame = new ClientBoardGame();
        this.myUsername = "";
    }

    public synchronized void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    public synchronized void updateStatus(int turn, int era, String username)
    {
        this.turn = turn;
        this.era = era;
        this.currentPlayer = username;
        myStatus = ClientStateName.WAIT_TURN;
        setChanged();
        notifyObservers(myStatus);
    }

    public synchronized void changeMyState(ClientStateName clientStateName)
    {
        myStatus = clientStateName;
        setChanged();
        notifyObservers(myStatus);
    }

    public synchronized void generateNewPlayerBoard(String username)
    {
        clientPlayerBoard.put(username, new ClientPlayerBoard(username));
    }

    public synchronized ClientPlayerBoard getClientPlayerBoard(String name) {
        return clientPlayerBoard.get(name);
    }

    public synchronized ClientBoardGame getClientBoardGame() {
        return clientBoardGame;
    }

    public synchronized PlayerBonusActions getPlayerBonusActions() {
        return playerBonusActions;
    }

    //*******


    public synchronized Map<String, ClientPlayerBoard> getClientPlayerBoard() {
        return clientPlayerBoard;
    }

    public synchronized int getEra() {
        return era;
    }

    public synchronized int getTurn() {
        return turn;
    }

    public synchronized String getCurrentPlayer() {
        return currentPlayer;
    }

    public synchronized String getMyUsername() {
        return myUsername;
    }
}
