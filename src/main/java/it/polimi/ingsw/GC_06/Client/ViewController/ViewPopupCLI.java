package it.polimi.ingsw.GC_06.Client.ViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 24/06/17.
 */
public class ViewPopupCLI implements Observer {

    private Map<ClientStateName, ViewPresenterCLI> clientStates = new HashMap<>();
    private MainClientModel mainClientModel;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private ViewOrchestratorCLI viewOrchestratorCLI;

    public ViewPopupCLI(MainClientModel mainClientModel, ClientNetworkOrchestrator clientNetworkOrchestrator, ViewOrchestratorCLI viewOrchestratorCLI)
    {
        this.mainClientModel = mainClientModel;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.viewOrchestratorCLI = viewOrchestratorCLI;
        this.initViews();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            ClientStateName clientStateName = (ClientStateName) arg;
            viewOrchestratorCLI.suspend();
            clientStates.get(clientStateName).viewWillAppear();
            viewOrchestratorCLI.resume();
        }
        catch (InterruptedException e)
        {
            return;
        }
    }

    private void initViews ()
    {
        clientStates.put(ClientStateName.CHOOSE_NEW_CARD, new PickOtherCardViewController(mainClientModel.getPlayerBonusActions(), mainClientModel.getClientBoardGame(), clientNetworkOrchestrator));
        clientStates.put(ClientStateName.MULTIPLE_PAYMENT, new PaymentWaysViewController(clientNetworkOrchestrator, mainClientModel.getPlayerBonusActions()));
        clientStates.put(ClientStateName.PARCHMENT, new ParchmentViewController(clientNetworkOrchestrator, mainClientModel.getPlayerBonusActions()));
        clientStates.put(ClientStateName.ASK_PRODHARV_CARDS, new AskUserProdHarv(mainClientModel.getPlayerBonusActions(), clientNetworkOrchestrator));
        clientStates.put(ClientStateName.POWERUP, new AskUserPowerUp(clientNetworkOrchestrator));
    }

}
