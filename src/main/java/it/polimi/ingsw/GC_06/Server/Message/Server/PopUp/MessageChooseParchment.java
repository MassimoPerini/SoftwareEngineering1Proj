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
public class MessageChooseParchment implements MessageServer {

    private List<ResourceSet> resourceSets;

    public MessageChooseParchment(List<ResourceSet> resourceSets) {
        this.resourceSets = new LinkedList<>();
        for (ResourceSet resourceSet : resourceSets) {
            this.resourceSets.add(new ResourceSet(resourceSet));
        }
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().changeParchment(resourceSets);
        clientController.getViewOrchestrator().change(ClientStateName.PARCHMENT, "");
    }
}
