package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 19/06/17.
 */
public class MessageLoggedIn implements MessageServer {

    private final String username;

    public MessageLoggedIn(String username) {
        this.username = username;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().setMyUsername(username);
        clientController.getViewOrchestrator().change(null, "LOGGED");
    }
}
