package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewController;
import org.jetbrains.annotations.NotNull;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 09/06/17.
 */
public class LoginViewController implements ViewController, Observer {

    @NotNull private CommandView commandView;
    @NotNull private ClientOrchestrator clientOrchestrator;

    public LoginViewController(@NotNull ClientOrchestrator clientOrchestrator)
    {
        this.commandView = new CmdView();
        this.clientOrchestrator = clientOrchestrator;
    }

    @Override
    public void viewWillAppear() {

        commandView.addLocalizedText("msg_login_start");
        commandView.addText("\n");
        commandView.addLocalizedText("username");
        String username = commandView.getString();
        clientOrchestrator.send(username);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("L'username non è valido");
    }
}
