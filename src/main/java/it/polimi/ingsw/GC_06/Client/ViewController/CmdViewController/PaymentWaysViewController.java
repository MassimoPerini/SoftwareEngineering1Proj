package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by massimo on 21/06/17.
 */
public class PaymentWaysViewController implements ViewPresenterCLI {

    private final CommandView commandView;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private final PlayerBonusActions playerBonusActions;

    public PaymentWaysViewController(ClientNetworkOrchestrator clientNetworkOrchestrator, PlayerBonusActions playerBonusActions)
    {
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.playerBonusActions = playerBonusActions;
        this.commandView = new CmdView();
    }

    @Override
    public void viewWillAppear() {
        commandView.addLocalizedText("Puoi scegliere diversi metodi di pagamento!");
        int i=0;
        for (ResourceSet resourceSet : playerBonusActions.getRequirementCard()) {
            for (Resource resource : resourceSet.getResources().keySet()) {
                commandView.addLocalizedText(resource.toString()+": "+resourceSet.getResources().get(resource)+", ");
            }
            i++;
        }
        int answ = commandView.getInt(0, i);

    }

    @Override
    public void addText(String txt) {

    }

    @Override
    public void viewWillDisappear() {

    }
}
