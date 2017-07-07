package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

/**
 * Created by massimo on 23/06/17.
 */
public class AskUserPowerUp implements ViewPresenterCLI {

    private CommandView commandView;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    public AskUserPowerUp(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() {
        commandView.addLocalizedText("Puoi scegliere se fare un powerup (digita valore [0-X])");
        int powerUp = commandView.getInt(0);
        MessageClient messageClient = new DefaultAnswer(powerUp);
        clientNetworkOrchestrator.send(messageClient);
    }

    @Override
    public void viewWillDisappear() {

    }
}
