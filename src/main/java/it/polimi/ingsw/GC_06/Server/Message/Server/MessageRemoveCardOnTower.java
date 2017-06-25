package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 14/06/17.
 */
public class MessageRemoveCardOnTower implements MessageServer {

    private String tower;
    private int plane;

    public MessageRemoveCardOnTower(String tower, int plane)
    {
        this.tower = tower;
        this.plane = plane;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getClientBoardGame().removeCard(tower, plane);
    }
}
