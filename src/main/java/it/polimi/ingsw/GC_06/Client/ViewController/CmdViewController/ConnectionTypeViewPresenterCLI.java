package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

import java.util.concurrent.Future;

/**
 * Created by massimo on 15/06/17.
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

    @Override
    public void viewWillAppear() {

        commandView.addLocalizedText("Come vuoi connetterti? 0:Socket, 1: RMI Tanto puoi scegliere solo socket...");
        answer = commandView.getInt(0,1);
        if (answer==0)
            clientNetworkOrchestrator.useSocket();
        else
            clientNetworkOrchestrator.useRMI();
        /*
        ExecutorService executor = Executors.newCachedThreadPool();
        this.future = executor.submit(this);
        */
    }

    @Override
    public void viewWillDisappear() {
     //   this.future.cancel(true);
    }

}
