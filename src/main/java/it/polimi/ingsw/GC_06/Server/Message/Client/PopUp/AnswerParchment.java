package it.polimi.ingsw.GC_06.Server.Message.Client.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by massimo on 21/06/17.
 */
public class AnswerParchment implements MessageClient {

    private final int choice;
    private String player;
    private int game;

    public AnswerParchment(int choice) {
        this.choice = choice;
    }


    @Override
    public void execute() {
        ResourceSet resourceSet = (FileLoader.getFileLoader().loadParchments())[choice];
        GameList.getInstance().getGameId(game).getGameStatus().getPlayers().get(player).variateResource(resourceSet);
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
