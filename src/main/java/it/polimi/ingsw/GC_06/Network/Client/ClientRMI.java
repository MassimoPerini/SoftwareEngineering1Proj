package it.polimi.ingsw.GC_06.Network.Client;

import it.polimi.ingsw.GC_06.Network.Message.MessageClient;

import java.util.Observable;

/**
 * Created by massimo on 12/06/17.
 */
public class ClientRMI extends Client {
    @Override
    public void submit(MessageClient action) {
        //Invio azioni
    }

    @Override
    public void submit(String string) {

    }

    @Override
    public void run() {
        //Ascolto e ricevo azioni
    }
}
