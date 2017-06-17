package it.polimi.ingsw.GC_06.Client;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by massimo on 15/06/17.
 */
public class ClientInputController implements Observer {

    @NotNull private final ClientNetworkOrchestrator clientNetworkOrchestrator;
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
    public void update(Observable o, Object arg) {
        MessageServer serverMessage = (MessageServer) arg;
        serverMessage.execute(clientController);
    }
}
