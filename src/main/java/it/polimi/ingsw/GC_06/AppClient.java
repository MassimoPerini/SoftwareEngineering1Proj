package it.polimi.ingsw.GC_06;


import com.airhacks.afterburner.injection.Injector;
import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.ClientInputController;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewOrchestratorCLI;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewOrchestratorFx;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPopupCLI;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPopupFx;

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
            ViewOrchestratorCLI viewOrchestratorCLI = new ViewOrchestratorCLI(clientInputController.getClientNetworkOrchestrator(), clientController.getMainClientModel());
            clientController.setViewOrchestrator(viewOrchestratorCLI);
            clientController.getMainClientModel().addObserver(viewOrchestratorCLI);

            ViewPopupCLI viewPopupCLI = new ViewPopupCLI(clientController.getMainClientModel(), clientNetworkOrchestrator, viewOrchestratorCLI);
            clientController.getMainClientModel().getPlayerBonusActions().addObserver(viewPopupCLI);

            clientController.getViewOrchestrator().execute(args);
        }
        else{
            ViewOrchestratorFx viewOrchestratorFx = new ViewOrchestratorFx();

            Map<Object, Object> customProperties = new HashMap<>();
            customProperties.put("clientInputController", clientInputController);
            customProperties.put("viewOrchestratorFx", viewOrchestratorFx);
            customProperties.put("clientNetworkOrchestrator", clientNetworkOrchestrator);
            customProperties.put("mainClientModel", clientController.getMainClientModel());
            customProperties.put("playerBonusActions", clientController.getMainClientModel().getPlayerBonusActions());

            Injector.setConfigurationSource(customProperties::get);
            clientController.setViewOrchestrator(viewOrchestratorFx);

            clientController.getMainClientModel().addObserver(clientController.getViewOrchestrator());

            ViewPopupFx viewPopupFx = new ViewPopupFx();
            clientController.getMainClientModel().getPlayerBonusActions().addObserver(viewPopupFx);

            clientController.getViewOrchestrator().execute(args);


        }
    }

}
