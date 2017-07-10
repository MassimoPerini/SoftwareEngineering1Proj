package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 09/07/17.
 */
public class MessagePlayerDisconnected implements MessageServer {

    private final String playerDisconnected;

    public MessagePlayerDisconnected(String username)
    {
        this.playerDisconnected = username;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().setUserDisconnected(playerDisconnected);
    }
}
