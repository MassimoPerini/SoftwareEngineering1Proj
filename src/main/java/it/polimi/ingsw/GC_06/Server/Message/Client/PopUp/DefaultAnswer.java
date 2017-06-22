package it.polimi.ingsw.GC_06.Server.Message.Client.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 21/06/17.
 */
public class DefaultAnswer implements MessageClient {

    private final int choice;
    private String player;
    private int game;

    public DefaultAnswer(int choice) {
        this.choice = choice;
    }


    @Override
    public void execute() {
        List<Integer> optPar = new LinkedList();
        optPar.add(choice);
        GameList.getInstance().getCurrentBlocking(GameList.getInstance().getGameId(game)).setOptionalParams(optPar);
    }

    @Override
    public void setGame(int game) {
        this.game = game;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }
}
