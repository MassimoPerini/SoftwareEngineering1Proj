package it.polimi.ingsw.GC_06.Network.Client;

import it.polimi.ingsw.GC_06.Network.Message.MessageClient;

/**
 * Created by massimo on 11/06/17.
 */
public interface ClientOutputHandler {

    void submit(MessageClient action);

}
