package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 10/07/17.
 */
public class MessageUserExcommunication implements MessageServer{

    private String user;
    private String excomm;

    public MessageUserExcommunication(String user, String excomm)
    {
        this.user = user;
        this.excomm = excomm;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getClientPlayerBoard().get(user).addExcommunication(excomm);
    }
}
