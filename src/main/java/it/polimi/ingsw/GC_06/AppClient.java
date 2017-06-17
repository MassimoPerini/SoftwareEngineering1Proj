package it.polimi.ingsw.GC_06;


import com.airhacks.afterburner.injection.Injector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.ClientInputController;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewOrchestratorCLI;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewOrchestratorFx;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.*;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.io.IOException;
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
        ClientController clientController = new ClientController();

        ClientNetworkOrchestrator clientNetworkOrchestrator = new ClientNetworkOrchestrator();
        ClientInputController clientInputController = new ClientInputController(clientNetworkOrchestrator, clientController);
        clientNetworkOrchestrator.addObserver(clientInputController);

        if (view==0) {
            ViewOrchestratorCLI viewOrchestratorCLI = new ViewOrchestratorCLI(clientInputController.getClientNetworkOrchestrator());
            clientController.setViewOrchestrator(viewOrchestratorCLI);
        }
        else{
            ViewOrchestratorFx viewOrchestratorFx = new ViewOrchestratorFx();

            Map<Object, Object> customProperties = new HashMap<>();
            customProperties.put("clientInputController", clientInputController);
            customProperties.put("viewOrchestratorFx", viewOrchestratorFx);
            customProperties.put("clientNetworkOrchestrator", clientNetworkOrchestrator);

            Injector.setConfigurationSource(customProperties::get);
            clientController.setViewOrchestrator(viewOrchestratorFx);
        }
        clientController.getViewOrchestrator().execute(args);
    }

}
