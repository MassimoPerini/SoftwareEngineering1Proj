package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.State.Game;

/**
 * Created by massimo on 19/06/17.
 */
public class MessageThrowDice implements MessageClient {

    private int game;




    @Override
    public void execute() {
        Game currGame = GameList.getInstance().getGameId(game);
        currGame.roll();
    }

    @Override
    public void setGame(int game) {
        this.game = game;
    }

    @Override
    public void setPlayer(String player) {

    }
}
