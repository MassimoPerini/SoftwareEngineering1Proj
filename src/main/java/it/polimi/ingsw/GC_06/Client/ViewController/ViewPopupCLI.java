package it.polimi.ingsw.GC_06.Client.ViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by massimo on 24/06/17.
 * This class is responsible for managing PopUps, which are used every time the model needs to ask for an input to
 * the client (CLI version)
 */
public class ViewPopupCLI implements Observer {

    private Map<ClientStateName, ViewPresenterCLI> clientStates = new HashMap<>();
    private MainClientModel mainClientModel;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private ViewOrchestratorCLI viewOrchestratorCLI;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Future future;

    public ViewPopupCLI(MainClientModel mainClientModel, ClientNetworkOrchestrator clientNetworkOrchestrator, ViewOrchestratorCLI viewOrchestratorCLI)
    {
        this.mainClientModel = mainClientModel;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.viewOrchestratorCLI = viewOrchestratorCLI;
        this.initViews();
    }

    /**
     * shows the necessary view to the client
     * @param o object observed
     * @param arg object passed during the notify process
     */
    @Override
    public void update(Observable o, Object arg) {
    /*    if (future!= null && !future.isDone())
        {
            return;
        }*/
        try {
            System.out.println("Displaying popup --- stopping main CLI");
            viewOrchestratorCLI.suspend();
            executorService.shutdownNow();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
            ClientStateName clientStateName = (ClientStateName) arg;
            executorService = Executors.newSingleThreadExecutor();
            future = executorService.submit(() -> {
                try {
                    clientStates.get(clientStateName).viewWillAppear();
                    viewOrchestratorCLI.resume();
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            });

        }catch (InterruptedException e){}
    }

    /**
     * initialize the necessary PopUp views
     */
    private void initViews ()
    {
        clientStates.put(ClientStateName.CHOOSE_NEW_CARD, new PickOtherCardViewController(mainClientModel.getPlayerBonusActions(), mainClientModel.getClientBoardGame(), clientNetworkOrchestrator));
        clientStates.put(ClientStateName.MULTIPLE_PAYMENT, new PaymentWaysViewController(clientNetworkOrchestrator, mainClientModel.getPlayerBonusActions()));
        clientStates.put(ClientStateName.PARCHMENT, new ParchmentViewController(clientNetworkOrchestrator, mainClientModel.getPlayerBonusActions()));
        clientStates.put(ClientStateName.ASK_PRODHARV_CARDS, new AskUserProdHarv(mainClientModel.getPlayerBonusActions(), clientNetworkOrchestrator));
        clientStates.put(ClientStateName.POWERUP, new AskUserPowerUp(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.CHOOSE_PERSONAL_BONUS, new AskUserPersonalBonus(clientNetworkOrchestrator, mainClientModel.getPlayerBonusActions()));
        clientStates.put(ClientStateName.EXCOMMUNICATION, new ExcommunicationViewController(clientNetworkOrchestrator));//POPUP
        clientStates.put(ClientStateName.USER_DISCONNECT, new UserDisconnectedController(mainClientModel, clientNetworkOrchestrator));
    }

}
