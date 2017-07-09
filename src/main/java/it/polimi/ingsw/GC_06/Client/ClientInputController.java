package it.polimi.ingsw.GC_06.Client;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 15/06/17.
 * this class handles inputs from the client, is an observer class
 */
public class ClientInputController implements Observer {

    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private final ClientController clientController;

    public ClientInputController(ClientNetworkOrchestrator clientNetworkOrchestrator, ClientController clientController)
    {
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.clientController = clientController;
    }


    public ClientNetworkOrchestrator getClientNetworkOrchestrator() {
        return clientNetworkOrchestrator;
    }

    public void send()
    {

    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        MessageServer serverMessage = (MessageServer) arg;
        serverMessage.execute(clientController);
    }
}
