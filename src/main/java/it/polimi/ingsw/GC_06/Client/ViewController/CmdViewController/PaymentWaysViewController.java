package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Resource.Resource;

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
        for (Requirement requirement : playerBonusActions.getRequirementCard()) {
            commandView.addLocalizedText(i+" Requirement: ");
            for (Resource resource : requirement.getRequirements().getResources().keySet()) {
                commandView.addLocalizedText("Resource "+resource.toString()+" value"+requirement.getRequirements().getResources().get(resource));
            }
            for (Resource resource : requirement.getCost().getResources().keySet()) {
                commandView.addLocalizedText("Cost "+resource.toString()+" value"+requirement.getCost().getResources().get(resource));
            }
            i++;
        }
        int answ = commandView.getInt(0, i);
        clientNetworkOrchestrator.send(new DefaultAnswer(answ));
    }

    @Override
    public void viewWillDisappear() {

    }
}
