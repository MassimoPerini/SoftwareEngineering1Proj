package it.polimi.ingsw.GC_06.Server.Message.Client.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.Map;

/**
 * Created by massimo on 23/06/17.
 */

public class ProdHarvAnswer implements MessageClient {

    private Map<String, Integer> choosen;
    private int game;
    private String player;

    public ProdHarvAnswer(Map<String, Integer> choosen)
    {
        this.choosen = choosen;
    }

    @Override
    public void execute() {
        Game currGame = GameList.getInstance().getGameId(game);
        GameList.getInstance().unlock(currGame, choosen);
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
