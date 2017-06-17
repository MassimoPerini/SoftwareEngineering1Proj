package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 17/06/17.
 */
public class MessageUpdateView implements MessageServer {

    private ClientStateName newState;
    private String text;

    public MessageUpdateView(ClientStateName newState, String text) {
        this.newState = newState;
        this.text = text;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getViewOrchestrator().change(newState, text);
    }
}
