package it.polimi.ingsw.GC_06;


import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.ClientInputController;
import it.polimi.ingsw.GC_06.Client.Network.ClientOrchestrator;
import it.polimi.ingsw.GC_06.Client.Network.ClientSocket;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.ConnectionTypeViewController;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.LoginViewController;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.FxLoader;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
        else
        {
            int select = JOptionPane.showOptionDialog(null, "Connessione", "Connessione", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (select == JOptionPane.NO_OPTION)
            {
                return;
            }

            clientOrchestrator =  new ClientOrchestrator(new ClientSocket(new Socket("127.0.0.1", 1337)));

            Map<Object, Object> customProperties = new HashMap<>();
            customProperties.put("clientOrchestrator", clientOrchestrator);
        /*
         * any function which accepts an Object as key and returns
         * and return an Object as result can be used as source.
         */
            Injector.setConfigurationSource(customProperties::get);
            FxLoader fxLoader = new FxLoader();
            fxLoader.initialize(args);
        }
    }
}
