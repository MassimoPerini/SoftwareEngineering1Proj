package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by giuseppe on 6/14/17.
 */
public class MessageUpdateResource implements MessageServer {

    private String username;
    private Map<Resource, Integer> newResource;

    public MessageUpdateResource (String username, ResourceSet newResource)
    {
        this.username = username;
        this.newResource = new HashMap<Resource, Integer>(newResource.getResources());
    }


    @Override
    public void execute(ClientController clientController) {
        ClientPlayerBoard clientPlayerBoard = clientController.getMainClientModel().getClientPlayerBoard(this.username);
        clientPlayerBoard.updateResourceSet(newResource);
    }
}
