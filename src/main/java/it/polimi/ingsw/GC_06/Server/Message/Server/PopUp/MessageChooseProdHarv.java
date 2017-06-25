package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 21/06/17.
 */
public class MessageChooseProdHarv implements MessageServer{

    private Map<String, List<Integer>> cards;

    public MessageChooseProdHarv(Map<String, List<Integer>> prodHarvEffects) {

        cards = new HashMap<>(prodHarvEffects);
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().setProdHarvAsk(cards);
    }
}
