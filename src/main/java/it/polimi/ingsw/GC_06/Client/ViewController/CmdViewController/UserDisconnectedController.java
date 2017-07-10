package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.MessageComeBack;

/**
 * Created by massimo on 09/07/17.
 */
public class UserDisconnectedController implements ViewPresenterCLI {

    private final CommandView commandView;
    private final MainClientModel mainClientModel;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;

    public UserDisconnectedController(MainClientModel mainClientModel, ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.mainClientModel = mainClientModel;
        this.commandView = new CmdView();
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() throws InterruptedException
    {
    //    mainClientModel.deleteObservers();
        if (mainClientModel.getPlayerBonusActions().getUserDisconnected().equals(mainClientModel.getMyUsername()))
        {
            commandView.addLocalizedText("Sei stato disconnesso, premi 1 per tornare in partita");
            int answ = commandView.getInt(1,1);
            clientNetworkOrchestrator.send(new MessageComeBack());
    //        mainClientModel.add
        }
        else{
            commandView.addLocalizedText("L'utente "+mainClientModel.getPlayerBonusActions().getUserDisconnected()+" si è disconnesso");
            commandView.print();
        }
    }
}
