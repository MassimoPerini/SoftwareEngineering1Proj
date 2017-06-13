package it.polimi.ingsw.GC_06.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Network.Client.ClientOrchestrator;
import it.polimi.ingsw.GC_06.Network.Message.Client.Login;
import it.polimi.ingsw.GC_06.View.CmdView;
import it.polimi.ingsw.GC_06.View.CommandView;
import it.polimi.ingsw.GC_06.ViewController.ViewController;
import org.jetbrains.annotations.NotNull;

/**
 * Created by massimo on 09/06/17.
 */
public class LoginViewController implements ViewController {

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
        commandView.addLocalizedText("username");
        String username = commandView.getString();
        clientOrchestrator.send(username);
    }
}
