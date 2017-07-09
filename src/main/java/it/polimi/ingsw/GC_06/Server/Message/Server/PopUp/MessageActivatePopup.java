package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 09/07/17.
 */
public class MessageActivatePopup implements MessageServer {

    private final ClientStateName clientStateName;

    public MessageActivatePopup (ClientStateName clientStateName)
    {
        this.clientStateName = clientStateName;
    }


    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().changeState(clientStateName);
    }
}
