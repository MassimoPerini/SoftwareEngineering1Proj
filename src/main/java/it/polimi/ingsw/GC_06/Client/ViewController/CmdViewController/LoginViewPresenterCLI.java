package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

import java.util.concurrent.Future;

/**
 * Created by massimo on 09/06/17.
 * this class is used to manage the client's login phase with the view
 */
public class LoginViewPresenterCLI implements ViewPresenterCLI {

    private CommandView commandView;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private Future future;

    public LoginViewPresenterCLI(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    /**
     * shows the view to login and manages client's interaction
     * @throws InterruptedException
     */
    @Override
    public void viewWillAppear() throws InterruptedException {
     //   this.future = executor.submit(this);
        commandView.addLocalizedText("msg_login_start");
        commandView.addText("\n");
        commandView.addLocalizedText("username");
        String username = commandView.getString();
        clientNetworkOrchestrator.send(username);
        commandView.addLocalizedText("Please wait...");

    }

    public synchronized void viewWillDisappear()
    {
 //       this.future.cancel(true);
    }

    public void run(){

    }
}
