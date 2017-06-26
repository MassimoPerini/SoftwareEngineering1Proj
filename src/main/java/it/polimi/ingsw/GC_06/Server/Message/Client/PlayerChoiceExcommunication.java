package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by massimo on 24/06/17.
 */
public class PlayerChoiceExcommunication implements MessageClient {

    private int game;
    private String player;
    private final boolean activateExcommunication;

    public PlayerChoiceExcommunication(boolean activateExcommunication)
    {
        this.activateExcommunication = activateExcommunication;
    }

    @Override
    public void execute() {
        Game currGame = GameList.getInstance().getGameId(game);
        Map<String, Boolean> choice = new HashMap<>();
        choice.put(player, activateExcommunication);
        GameList.getInstance().unlock(currGame, choice);
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
