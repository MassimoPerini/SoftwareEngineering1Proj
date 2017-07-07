package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Future;

/**
 * Created by massimo on 09/06/17.
 */
public class LoginViewPresenterCLI implements ViewPresenterCLI {

    @NotNull private CommandView commandView;
    @NotNull private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private Future future;

    public LoginViewPresenterCLI(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

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
