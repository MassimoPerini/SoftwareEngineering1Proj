package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientBoardGame;
import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.AnswerPickOtherCard;

import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 21/06/17.
 */
public class PickOtherCardViewController implements ViewPresenterCLI {

    private final CommandView commandView;
    private PlayerBonusActions playerBonusActions;
    private final ClientBoardGame clientBoardGame;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;

    public PickOtherCardViewController(PlayerBonusActions playerBonusActions, ClientBoardGame clientBoardGame, ClientNetworkOrchestrator clientNetworkOrchestrator) {
        commandView = new CmdView();
        this.playerBonusActions = playerBonusActions;
        this.clientBoardGame = clientBoardGame;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }


    @Override
    public void viewWillAppear() {
        commandView.addLocalizedText("Puoi prendere un'altra carta (se non la vuoi, premi z, altrimenti premi qualcos'altro)!");
        String answ = commandView.getString();
        if (answ.equals("z"))
        {
            return;
        }
        Map<String, List<Integer>> towers = playerBonusActions.getPickAnotherCard();

        for (String tower : towers.keySet()) {
            commandView.addLocalizedText("TORRE "+tower+"\n");
            List<Integer> floors = towers.get(tower);
            for (Integer floor : floors) {
                ClientTowerFloor clientTowerFloor = clientBoardGame.getTowersClient().get(tower).get(floor.intValue());
                commandView.addLocalizedText("PIANO "+floor+"CARTA: "+clientTowerFloor.getCard()+"\n");
            }
        }

        commandView.addLocalizedText("Inserisci la torre");
        String tower = commandView.getString();
        List<Integer> floors = towers.get(tower);
        commandView.addLocalizedText("piani:");
        int i=0;
        for (Integer floor : floors) {
            commandView.addLocalizedText("Scelta "+i+": "+String.valueOf(floor.intValue())+"\n");
            i++;
        }
        commandView.addLocalizedText("INDICE della tua scelta: ");
        int floor = commandView.getInt(0, i);
        AnswerPickOtherCard answerPickOtherCard = new AnswerPickOtherCard(floors.get(floor), tower);
        clientNetworkOrchestrator.send(answerPickOtherCard);
    }

    @Override
    public void addText(String txt) {

    }

    @Override
    public void viewWillDisappear() {

    }
}
