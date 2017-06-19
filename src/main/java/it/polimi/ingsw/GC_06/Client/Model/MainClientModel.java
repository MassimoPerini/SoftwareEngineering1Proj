package it.polimi.ingsw.GC_06.Client.Model;

import javafx.beans.property.SimpleListProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 6/14/17.
 */
public class MainClientModel {
    private final Map<String, ClientPlayerBoard> clientPlayerBoard;
    private final ClientBoardGame clientBoardGame;
    private int era, turn;
    private String currentPlayer;
    private String myUsername;

    public MainClientModel()
    {
        clientPlayerBoard = new HashMap<>();
        this.clientBoardGame = new ClientBoardGame();
        this.myUsername = "";
    }


    public void updateStatus(int turn, int era, String username)
    {
        this.turn = turn;
        this.era = era;
        this.currentPlayer = username;
    }

    public void generateNewPlayerBoard(String username)
    {
        clientPlayerBoard.put(username, new ClientPlayerBoard(username));
    }

    public ClientPlayerBoard getClientPlayerBoard(String name) {
        return clientPlayerBoard.get(name);
    }

    public ClientBoardGame getClientBoardGame() {
        return clientBoardGame;
    }


    //*******


    public Map<String, ClientPlayerBoard> getClientPlayerBoard() {
        return clientPlayerBoard;
    }

    public int getEra() {
        return era;
    }

    public int getTurn() {
        return turn;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getMyUsername() {
        return myUsername;
    }
}
