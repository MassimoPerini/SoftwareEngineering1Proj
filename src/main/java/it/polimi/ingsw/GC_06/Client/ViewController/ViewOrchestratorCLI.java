package it.polimi.ingsw.GC_06.Client.ViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.ConnectionTypeViewPresenterCLI;
import it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController.LoginViewPresenterCLI;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by massimo on 16/06/17.
 */
public class ViewOrchestratorCLI implements ViewOrchestrator{

    private Map<ClientStateName, ViewPresenterCLI> clientStates;
    private ClientStateName currentState = ClientStateName.START;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    public ViewOrchestratorCLI(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.clientStates = new HashMap<>();
        for (ClientStateName stateName : ClientStateName.values()) {
            this.clientStates.put(stateName, null);
        }
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.generateViewCli();
    }

    private void generateViewCli()
    {
        clientStates.put(ClientStateName.LOGIN, new LoginViewPresenterCLI(clientNetworkOrchestrator));
        clientStates.put(ClientStateName.START, new ConnectionTypeViewPresenterCLI(clientNetworkOrchestrator));
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

}
