package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 20/06/17.
 */
public class MessageChoosePayment implements MessageServer {

    private List<ResourceSet> resourceSets;

    public MessageChoosePayment(List<ResourceSet> resourceSets) {
        this.resourceSets = new LinkedList<>();
        for (ResourceSet resourceSet : resourceSets) {
            this.resourceSets.add(new ResourceSet(resourceSet));
        }
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().setRequirementCard(resourceSets);
        clientController.getViewOrchestrator().change(ClientStateName.MULTIPLE_PAYMENT, "");
    }

}
