package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 17/06/17.
 */
public class MessageGameStarted implements MessageServer {

    @Override
    public void execute(ClientController clientController) {
        //Complete here
        clientController.getViewOrchestrator().change(ClientStateName.GAME_START, "");

    }
}
