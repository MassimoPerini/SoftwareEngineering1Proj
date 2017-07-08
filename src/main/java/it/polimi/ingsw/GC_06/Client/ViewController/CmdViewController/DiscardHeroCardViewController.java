package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.DiscardHeroCardMessage;
import it.polimi.ingsw.GC_06.model.Loader.Setting;

/**
 * Created by giuseppe on 7/8/17.
 */
public class DiscardHeroCardViewController implements ViewPresenterCLI {

    private final CommandView commandView;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private final ClientPlayerBoard clientPlayerBoard;
    private int discardedHeroCards;

    public DiscardHeroCardViewController(ClientPlayerBoard clientPlayerBoard,ClientNetworkOrchestrator clientNetworkOrchestrator) {
        commandView = new CmdView();
        this.clientPlayerBoard = clientPlayerBoard;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() throws InterruptedException {

        commandView.addLocalizedText("Puoi Scartare una carta");
        commandView.addText("\n");
        commandView.addLocalizedText("Che carte vuoi Scartate?");
        int i = 0;
        for (String s : clientPlayerBoard.getHeroCards()) {

            System.out.println(String.valueOf(i) + " " + Setting.getInstance().getProperty(s) +  "   " + s);
            i++;
        }
        discardedHeroCards = commandView.getInt(0,clientPlayerBoard.getHeroCards().size()-1);
        clientNetworkOrchestrator.send(new DiscardHeroCardMessage(discardedHeroCards));

    }
}
