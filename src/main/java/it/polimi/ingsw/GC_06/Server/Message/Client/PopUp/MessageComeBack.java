package it.polimi.ingsw.GC_06.Server.Message.Client.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.Server.Network.LoginHub;

/**
 * Created by massimo on 09/07/17.
 */
public class MessageComeBack implements MessageClient {

    int gameIndex;
    String player;

    public MessageComeBack()
    {
        super();
    }

    @Override
    public void execute() {
        LoginHub.getInstance().removeAlreadyNotified(player);
        GameList.getInstance().getGameId(gameIndex).getGameStatus().getPlayers().get(player).setConnected(true);
    }

    @Override
    public void setGame(int game) {
        this.gameIndex = game;
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
