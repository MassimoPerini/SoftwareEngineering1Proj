package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 23/06/17.
 */
public class MessageChoosePowerUp implements MessageServer {


    public MessageChoosePowerUp() {
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getPlayerBonusActions().changePowerUp(true);
    }
}
