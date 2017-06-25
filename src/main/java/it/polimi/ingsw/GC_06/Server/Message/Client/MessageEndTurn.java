package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.EndTurn;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 20/06/17.
 */
public class MessageEndTurn implements MessageClient {

    private int game;
    private String player;

    @Override
    public void execute() {
        Game currentGame = GameList.getInstance().getGameId(game);
        EndTurn endTurn = new EndTurn(currentGame);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(endTurn);

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
}
