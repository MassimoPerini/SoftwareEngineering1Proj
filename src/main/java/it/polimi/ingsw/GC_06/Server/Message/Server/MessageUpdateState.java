package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 24/06/17.
 */
public class MessageUpdateState implements MessageServer {

    private final ClientStateName clientStateName;

    public MessageUpdateState(ClientStateName clientStateName)
    {
        this.clientStateName = clientStateName;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().changeMyState(clientStateName);
    }
}
