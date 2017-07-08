package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Action.EndGame.PersonalStatistics;

import java.util.HashMap;
import java.util.List;
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
    private int era, turn;
    private String currentPlayer;
    private String myUsername;
    private List<PersonalStatistics> personalStatistics;
    private PlayerColors playerColors;

    public MainClientModel(PlayerColors playerColors)
    {
        playerBonusActions = new PlayerBonusActions();
        clientPlayerBoard = new HashMap<>();
        this.clientBoardGame = new ClientBoardGame();
        this.myUsername = "";
        this.playerColors = playerColors;
    }

    public synchronized void setMyUsername(String myUsername) {
        this.myUsername = myUsername;
    }

    public void setPersonalStatistics(List<PersonalStatistics> personalStatistics) {
        this.personalStatistics = personalStatistics;
        myStatus = ClientStateName.END_GAME;
        setChanged();
        notifyObservers(myStatus);
    }

    public List<PersonalStatistics> getPersonalStatistics() {
        // qua si deve ordinare


        return personalStatistics;
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

    public synchronized void generateNewPlayerBoard(String username, List<String> customResources)
    {
        clientPlayerBoard.put(username, new ClientPlayerBoard(username, customResources));
        // add color
        playerColors.addPlayer(username);
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

    public ClientStateName getMyStatus() {
        return myStatus;
    }




}
