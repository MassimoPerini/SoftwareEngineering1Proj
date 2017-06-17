package it.polimi.ingsw.GC_06.Client.Model;

import javafx.beans.property.SimpleListProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 6/14/17.
 */
public class MainClientModel {
    private Map<String, ClientPlayerBoard> clientPlayerBoard;
    private ClientBoardGame clientBoardGame;


    public ClientPlayerBoard getClientPlayerBoard(String name) {
        return clientPlayerBoard.get(name);
    }

    public ClientBoardGame getClientBoardGame() {
        return clientBoardGame;
    }
}
