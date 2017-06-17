package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

/**
 * Created by massimo on 15/06/17.
 */
public class ConnectionTypeViewPresenterCLI implements ViewPresenterCLI {

    private final CommandView commandView;
    private int answer;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    public ConnectionTypeViewPresenterCLI(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() {
        commandView.addLocalizedText("Come vuoi connetterti? 0:Socket, 1: RMI Tanto puoi scegliere solo socket...");
        answer = commandView.getInt(0,0);
        if (answer==0)
                clientNetworkOrchestrator.useSocket();
        else
            clientNetworkOrchestrator.useRMI();
    }

    @Override
    public void addText(String txt) {
        commandView.addLocalizedText(txt);
        commandView.print();
    }

}
