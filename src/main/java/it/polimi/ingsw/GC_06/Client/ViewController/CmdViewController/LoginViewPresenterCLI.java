package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    public void viewWillAppear() {
        ExecutorService executor = Executors.newCachedThreadPool();
        this.future = executor.submit(this);

    }

    public synchronized void viewWillDisappear()
    {
        this.future.cancel(true);
    }

    @Override
    public void addText(String txt) {
        commandView.addLocalizedText(txt);
        commandView.print();
    }

    @Override
    public void run() {
            commandView.addLocalizedText("msg_login_start");
            commandView.addText("\n");
            commandView.addLocalizedText("username");
            String username = commandView.getString();
            clientNetworkOrchestrator.send(username);
        commandView.addLocalizedText("Please wait...");
    }
}
