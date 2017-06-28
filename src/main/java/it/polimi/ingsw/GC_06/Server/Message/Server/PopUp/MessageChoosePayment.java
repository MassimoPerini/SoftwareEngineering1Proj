package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Card.Requirement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 20/06/17.
 */
public class MessageChoosePayment implements MessageServer {

    private List<Requirement> requirements;

    public MessageChoosePayment(List<Requirement> resourceSets) {
        this.requirements = new LinkedList<>();

        for (Requirement requirement : resourceSets) {
            this.requirements.add(new Requirement(requirement));
        }
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().setRequirementCard(requirements);
    }

}
