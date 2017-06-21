package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 21/06/17.
 */
public class MessageChooseProdHarv implements MessageServer{

    private Map<String, List<Integer>> cards;

    public MessageChooseProdHarv(Map<String, List<Integer>> cards) {
        this.cards = cards;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().setProdHarvAsk(cards);
        clientController.getViewOrchestrator().change(ClientStateName.ASK_PRODHARV_CARDS, "");
    }
}
