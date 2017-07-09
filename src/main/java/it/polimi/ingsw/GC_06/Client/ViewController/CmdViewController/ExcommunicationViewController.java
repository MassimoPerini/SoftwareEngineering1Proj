package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PlayerChoiceExcommunication;

/**
 * Created by massimo on 24/06/17.
 * this class is used to manage the client's choice during the "rapporto al vaticano" with the view
 */
public class ExcommunicationViewController implements ViewPresenterCLI {

    private final CommandView commandView = new CmdView();
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;

    public ExcommunicationViewController(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    /**
     * shows the view for selecting wether to pay faithpoints or not and manages client's choice
     * @throws InterruptedException
     */
    @Override
    public void viewWillAppear() throws InterruptedException {
        commandView.addLocalizedText("Vuoi essere scomunicato? 0: sì, 1:no");
        int answ = commandView.getInt(0, 1);
        boolean answer = (answ == 0);
        clientNetworkOrchestrator.send(new PlayerChoiceExcommunication(answer));
    }
}
