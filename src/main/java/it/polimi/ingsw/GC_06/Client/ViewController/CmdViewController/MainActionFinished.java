package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageEndTurn;

/**
 * Created by massimo on 25/06/17.
 */
public class MainActionFinished implements ViewPresenterCLI {

    private ClientNetworkOrchestrator clientNetworkOrchestrator;
    CommandView commandView = new CmdView();

    public MainActionFinished(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() throws InterruptedException {

        commandView.addLocalizedText("premi l per passare");
        String answ = commandView.getString();
        if (answ.equals("l")) {
            MessageEndTurn messageEndTurn = new MessageEndTurn();
            clientNetworkOrchestrator.send(messageEndTurn);
        }

    }

}
