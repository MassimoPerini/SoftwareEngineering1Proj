package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 20/06/17.
 */
public class MessagePickAnotherCard implements MessageServer {

    private List<Map<String, Integer>> floors;

    public MessagePickAnotherCard(List<Map<String, Integer>> floors) {
        this.floors = floors;
    }

    @Override
    public void execute(ClientController clientController) {
        List<ClientTowerFloor> clientTowerFloors = new LinkedList<>();

        for (Map<String, Integer> floor : floors) {
            for (String s : floor.keySet()) {
                List<ClientTowerFloor> towerFloors = clientController.getMainClientModel().getClientBoardGame().getTowersClient().get(s);
                ClientTowerFloor clientTowerFloor = towerFloors.get(floor.get(s));
                clientTowerFloors.add(clientTowerFloor);
            }
        }
        clientController.getMainClientModel().getPlayerBonusActions().setPickAnotherCard(clientTowerFloors);
    }
}
