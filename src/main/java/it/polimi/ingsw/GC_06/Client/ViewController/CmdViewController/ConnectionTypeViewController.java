package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientOrchestrator;
import it.polimi.ingsw.GC_06.Client.Network.ClientSocket;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewController;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by massimo on 15/06/17.
 */
public class ConnectionTypeViewController implements ViewController {

    private final CommandView commandView;
    private int answer;

    public ConnectionTypeViewController()
    {
        commandView = new CmdView();
    }

    @Override
    public void viewWillAppear() {
        commandView.addLocalizedText("Come vuoi connetterti? 0:Socket, 1: RMI Tanto puoi scegliere solo socket...");
        answer = commandView.getInt(0,0);
    }

    public ClientOrchestrator getAnswer() throws IOException {
        return new ClientOrchestrator(new ClientSocket(new Socket("127.0.0.1", 1337)));
    }
}
