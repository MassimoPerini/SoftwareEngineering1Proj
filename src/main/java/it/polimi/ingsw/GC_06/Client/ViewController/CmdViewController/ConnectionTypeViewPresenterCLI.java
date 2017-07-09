package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

import java.util.concurrent.Future;

/**
 * Created by massimo on 15/06/17.
 * this class is used to manage the client's choice of the type of connection (Socket/RMI) with the view
 */
public class ConnectionTypeViewPresenterCLI implements ViewPresenterCLI {

    private final CommandView commandView;
    private int answer;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private Future future;

    public ConnectionTypeViewPresenterCLI(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    /**
     * shows the view for selceting the connection and manages client's choice
     * @throws InterruptedException
     */
    @Override
    public void viewWillAppear() throws InterruptedException {

        commandView.addLocalizedText("Come vuoi connetterti? 0:Socket, 1: RMI Tanto puoi scegliere solo socket...");
        answer = commandView.getInt(0,1);
        if (answer==0)
            clientNetworkOrchestrator.useSocket();
        else
            clientNetworkOrchestrator.useRMI();
    }

}
