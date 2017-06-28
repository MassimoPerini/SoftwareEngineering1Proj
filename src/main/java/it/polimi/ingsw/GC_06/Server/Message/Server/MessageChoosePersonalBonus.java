package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.List;

/**
 * Created by massimo on 27/06/17.
 */
public class MessageChoosePersonalBonus implements MessageServer {

    private List<String> playerBoard;

    public MessageChoosePersonalBonus(List<String> playerBoard)
    {
        this.playerBoard = playerBoard;
    }

    @Override
    public void execute(ClientController clientController) {
        //Set playerBoard
        clientController.getMainClientModel().getPlayerBonusActions().setPersonalBonusOptions(playerBoard);

    }
}
