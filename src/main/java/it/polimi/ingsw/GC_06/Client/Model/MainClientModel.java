package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.State.StateName;

import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 6/14/17.
 */
public class MainClientModel {
    private ClientBoardGame board;
    private Map<String,ClientPlayerBoard> clientPlayerBoard;
    private Map<StateName, ClientState> clientStates;
    private ClientState currentState;

    public ClientPlayerBoard getClientPlayerBoard(String username) {
        return clientPlayerBoard.get(username);
    }

    public ClientBoardGame getClientBoardGame()
    {
        return this.board;
    }
}
