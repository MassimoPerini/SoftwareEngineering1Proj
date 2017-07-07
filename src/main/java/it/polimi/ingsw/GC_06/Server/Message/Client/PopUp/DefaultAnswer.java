package it.polimi.ingsw.GC_06.Server.Message.Client.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;

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
        Integer integer = choice;
        GameList.getInstance().unlock(GameList.getInstance().getGameId(game), integer);
    }

    @Override
    public void setGame(int game) {
        this.game = game;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }


    @Override
    public void run() {
        execute();
    }
}
