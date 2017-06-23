package it.polimi.ingsw.GC_06.Client.ViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 16/06/17.
 */
public class ViewOrchestratorCLI implements ViewOrchestrator, Observer{

    private Map<ClientStateName, ViewPresenterCLI> clientStates;
    private ClientStateName currentState = ClientStateName.START;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private MainClientModel mainClientModel;
    private Map<String, ClientPlayerBoard> clientPlayerBoard;

    public ViewOrchestratorCLI(ClientNetworkOrchestrator clientNetworkOrchestrator, MainClientModel mainClientModel)
    {
        this.clientStates = new HashMap<>();
        for (ClientStateName stateName : ClientStateName.values()) {
            this.clientStates.put(stateName, null);
        }
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.mainClientModel = mainClientModel;


        this.generateViewCli();
    }

    private void generateViewCli()
    {
        clientStates.put(ClientStateName.LOGIN, new LoginViewPresenterCLI(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.START, new ConnectionTypeViewPresenterCLI(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.GAME_START, new BoardStatusViewController(mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard()));     //TEST, Observer?
        clientStates.put(ClientStateName.MY_TURN, new UserActionViewController(mainClientModel, clientNetworkOrchestrator));
    //    clientStates.put(ClientStateName.ASK_PRODHARV_CARDS);
        clientStates.put(ClientStateName.CHOOSE_NEW_CARD, new PickOtherCardViewController(mainClientModel.getPlayerBonusActions(), mainClientModel.getClientBoardGame(), clientNetworkOrchestrator));
        clientStates.put(ClientStateName.MULTIPLE_PAYMENT, new PaymentWaysViewController(clientNetworkOrchestrator, mainClientModel.getPlayerBonusActions()));
        clientStates.put(ClientStateName.PARCHMENT, new ParchmentViewController(clientNetworkOrchestrator, mainClientModel.getPlayerBonusActions()));
        clientStates.put(ClientStateName.ASK_PRODHARV_CARDS, new AskUserProdHarv(mainClientModel.getPlayerBonusActions(), clientNetworkOrchestrator));
        clientStates.put(ClientStateName.POWERUP, new AskUserPowerUp(clientNetworkOrchestrator));


    }

    @Override
    public void change(ClientStateName state, String property) {
        try {
            if (state == null) {
                if (property != null) {
                    clientStates.get(currentState).addText(property);
                }
                return;
            }
            clientStates.get(currentState).viewWillDisappear();
            currentState = state;
            if (property != null)
                clientStates.get(currentState).addText(property);
            clientStates.get(currentState).viewWillAppear();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(String [] args)
    {
        clientStates.get(currentState).viewWillAppear();
        this.change(ClientStateName.LOGIN, null);
    }

    //L'era o qualcos'altro è finita
    @Override
    public void update(Observable o, Object arg) {
        if (mainClientModel.getCurrentPlayer().equals(mainClientModel.getMyUsername()))
        {
            this.change(ClientStateName.MY_TURN, "");
        }
        else{
            this.change(ClientStateName.GAME_START, "");
        }
    }
}
