package it.polimi.ingsw.GC_06.Server.Message;

import it.polimi.ingsw.GC_06.Client.ClientController;

/**
 * Created by massimo on 12/06/17.
 */
public interface MessageServer {

    void execute(ClientController clientController);
}
