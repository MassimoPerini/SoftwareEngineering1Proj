package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 20/06/17.
 */
public class MessagePickAnotherCard implements MessageServer {

    private Map<String, List<Integer>> floors;

    public MessagePickAnotherCard(Map<String, List<Integer>> floors) {
        this.floors = floors;
    }

    @Override
    public void execute(ClientController clientController) {
        List<ClientTowerFloor> result = new LinkedList<>();
        for (String s : floors.keySet()) {
            for (Integer index : floors.get(s)) {
                result.add(clientController.getMainClientModel().getClientBoardGame().getTowersClient().get(s).get(index));
            }
        }
        clientController.getMainClientModel().getPlayerBonusActions().setPickAnotherCard(result);
        clientController.getViewOrchestrator().change(ClientStateName.CHOOSE_NEW_CARD, "");
    }
}
