package it.polimi.ingsw.GC_06;


import it.polimi.ingsw.GC_06.Client.ClientInputController;
import it.polimi.ingsw.GC_06.Client.Network.ClientOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.ConnectionTypeViewController;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.LoginViewController;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class AppClient {

    //Main controller
    //http://stackoverflow.com/questions/34712885/how-to-load-an-external-properties-file-from-a-maven-java-project
    //http://www.avajava.com/tutorials/lessons/how-do-i-read-a-properties-file-with-a-resource-bundle.html

    public static void main(String[] args) throws IOException {

        CommandView commandView = new CmdView();
        commandView.addLocalizedText("Che interfaccia vuoi? 0: CLI, 1: GUI");
        int view = commandView.getInt(0,1);
        ClientOrchestrator clientOrchestrator;

        if (view == 0)
        {
            ConnectionTypeViewController connectionTypeViewController = new ConnectionTypeViewController();
            connectionTypeViewController.viewWillAppear();
            clientOrchestrator = connectionTypeViewController.getAnswer();

            ClientInputController clientInputController = new ClientInputController();
            clientOrchestrator.addObserver(clientInputController);

            LoginViewController loginViewController = new LoginViewController(clientOrchestrator);
            loginViewController.viewWillAppear();
            System.out.println("Sarà avvenuto il login?");
        }
    }
}
