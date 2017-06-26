package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.EndTurn;
import it.polimi.ingsw.GC_06.model.State.Game;

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

        if (endTurn.isAllowed())
        {
            endTurn.execute();
        }

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
