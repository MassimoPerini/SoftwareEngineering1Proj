package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.PlayerHeroCardChoices;
import it.polimi.ingsw.GC_06.model.Loader.Setting;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by giuseppe on 7/5/17.
 */
public class PlayHeroCardViewController  implements ViewPresenterCLI{

    private final CommandView commandView;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private final ClientPlayerBoard clientPlayerBoard;
    private List<Integer> heroCardsIndexes = new LinkedList<>();


    public PlayHeroCardViewController(ClientPlayerBoard clientPlayerBoard,ClientNetworkOrchestrator clientNetworkOrchestrator) {
        commandView = new CmdView();
        this.clientPlayerBoard = clientPlayerBoard;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() throws InterruptedException {
        commandView.addLocalizedText("Puoi Giocare una favolosa Hero Card");
        commandView.addText("\n");
        commandView.addLocalizedText("Che carte vuoi?");
        for (String s : clientPlayerBoard.getHeroCards()) {
            System.out.println(Setting.getInstance().getProperty(s));
        }
        heroCardsIndexes = commandView.getList(0,clientPlayerBoard.getHeroCards().size()-1);
        clientNetworkOrchestrator.send(new PlayerHeroCardChoices(heroCardsIndexes));



    }
}
