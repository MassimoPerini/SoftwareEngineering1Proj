package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

/**
 * Created by massimo on 23/06/17.
 * this class is used to manage the client's choice for powering up a familyMember with the view
 */
public class AskUserPowerUp implements ViewPresenterCLI {

    private CommandView commandView;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    public AskUserPowerUp(ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    /**
     * shows the view for the powerUp and manages client's choice
     * @throws InterruptedException
     */
    @Override
    public void viewWillAppear() throws InterruptedException {
        commandView.addLocalizedText("Puoi scegliere se fare un powerup (digita valore [0-X])");
        int powerUp = commandView.getInt(0);
        MessageClient messageClient = new DefaultAnswer(powerUp);
        clientNetworkOrchestrator.send(messageClient);
    }
}
