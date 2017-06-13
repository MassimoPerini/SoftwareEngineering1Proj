package it.polimi.ingsw.GC_06.Network.Message.Client;

import it.polimi.ingsw.GC_06.Network.Message.MessageClient;
import it.polimi.ingsw.GC_06.model.State.Game;

/**
 * Created by massimo on 11/06/17.
 */
public class Login implements MessageClient {

    private String username;

    public Login(String username) {
        this.username = username;
    }

    @Override
    public void execute() {
    }

    @Override
    public void setGame(int game) {

    }

    @Override
    public void setPlayer(String player) {

    }
}
