package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by massimo on 21/06/17.
 * this class is used to manage the client's choice of council privilege with the view
 */
public class ParchmentViewController implements ViewPresenterCLI {

    private final CommandView commandView;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private final PlayerBonusActions playerBonusActions;

    public ParchmentViewController(ClientNetworkOrchestrator clientNetworkOrchestrator, PlayerBonusActions playerBonusActions) {
        this.commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.playerBonusActions = playerBonusActions;
    }


    /**
     * shows the view to select the desired council privilege and manages client's choice
     * @throws InterruptedException
     */
    @Override
    public void viewWillAppear() throws InterruptedException {
        commandView.addLocalizedText("Ti è stato concesso un privilegio del consiglio\n");
        int i=0;
        for (ResourceSet resourceSet : playerBonusActions.getParchmentList()) {
            commandView.addLocalizedText("Scelta "+i+":\n");
            for (Resource resource : resourceSet.getResources().keySet()) {
                commandView.addText(resource.toString()+": "+resourceSet.getResources().get(resource)+", ");
            }
            i++;
        }

        int answ = commandView.getInt(0, i);
        DefaultAnswer defaultAnswer = new DefaultAnswer(answ);
        clientNetworkOrchestrator.send(defaultAnswer);

    }
}
