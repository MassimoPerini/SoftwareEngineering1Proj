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
 * this class is used to manage the client's slection of the hero card to play with the view
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

    /**
     * shows the view to select the desired hero card and manages client's choice
     * @throws InterruptedException
     */
    @Override
    public void viewWillAppear() throws InterruptedException {
        commandView.addLocalizedText("Puoi Giocare una favolosa Hero Card");
        commandView.addText("\n");
        commandView.addLocalizedText("Che carte vuoi?");
        int i = 0;
        for (String s : clientPlayerBoard.getHeroCards()) {
            System.out.println(String.valueOf(i) + Setting.getInstance().getProperty(s) +  "   " + s);
        }
        heroCardsIndexes = commandView.getList(0,clientPlayerBoard.getHeroCards().size()-1);
        clientNetworkOrchestrator.send(new PlayerHeroCardChoices(heroCardsIndexes));



    }
}
