package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 24/06/17.
 */
public class MessageError implements MessageServer {

    private String text;

    public MessageError(String text)
    {
        this.text = text;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().changeMyState(ClientStateName.LOGIN);
    }
}
