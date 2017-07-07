package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientBoardGame;
import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;

import java.util.List;

/**
 * Created by massimo on 20/06/17.
 */
public class TutorialPickCard implements ViewPresenterCLI {

    private final CommandView commandView;
    private final ClientBoardGame clientBoardGame;
    private final ClientPlayerBoard clientPlayerBoard;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;

    public TutorialPickCard(CommandView commandView, ClientBoardGame clientBoardGame, ClientPlayerBoard clientPlayerBoard, ClientNetworkOrchestrator clientNetworkOrchestrator) {
        this.commandView = commandView;
        this.clientBoardGame = clientBoardGame;
        this.clientPlayerBoard = clientPlayerBoard;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear(){
        commandView.addLocalizedText("Vuoi prendere una carta...");

        commandView.addLocalizedText("Ok, questi sono i tuoi familiari:\n");
        int a = 1;
        for (ClientFamilyMember clientFamilyMember : clientPlayerBoard.getFamilyMembers()) {
            commandView.addText(clientFamilyMember.getColor()+ " "+clientFamilyMember.getValue()+"\n");
            a++;
        }

        String [] towers = new String [clientBoardGame.getTowersClient().keySet().size()];

        int maxFloor=0;
        commandView.addLocalizedText("Ok, queste sono le torri con le loro carte:");
        int i=1;
        for (String tower : clientBoardGame.getTowersClient().keySet()) {
            commandView.addLocalizedText("Torre: "+i+"\n");
            towers[i-1] = tower;
            List<ClientTowerFloor> clientTowerFloors = clientBoardGame.getTowersClient().get(tower);
            int j=1;
            for (ClientTowerFloor clientTowerFloor : clientTowerFloors) {
                if (j>maxFloor) {
                    maxFloor = j;
                }
                commandView.addText(j+" Card: "+clientTowerFloor.getCard()+" players: ");
                for (ClientFamilyMember clientFamilyMember : clientTowerFloor.getSpaceAction().getFamilyMembers()) {
                    commandView.addText(clientFamilyMember.getPlayer());
                }
                j++;
            }
            i++;
        }



        int max = clientBoardGame.getTowersClient().size();
        commandView.addLocalizedText("Che torre vuoi? [1-"+max+"]");
        int val = commandView.getInt(1, max);
        String tower = towers[val-1];
        commandView.addLocalizedText("Che piano vuoi? [1-"+maxFloor+"]");
        int floor = (commandView.getInt(1, maxFloor)-1);
        commandView.addLocalizedText("Con che familiare? [1-"+a+"]");
        int familyMember = (commandView.getInt(1, a)-1);
        commandView.addLocalizedText("Di quanto il power-up");
        int powerUp = (commandView.getInt(0));

        MessageBoardActionTower messageBoardActionTower = new MessageBoardActionTower(tower, floor, familyMember,powerUp);
        clientNetworkOrchestrator.send(messageBoardActionTower);
    }
}
