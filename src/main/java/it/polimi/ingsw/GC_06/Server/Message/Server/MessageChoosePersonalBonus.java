package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.List;

/**
 * Created by massimo on 27/06/17.
 */
public class MessageChoosePersonalBonus implements MessageServer {

    private List<Integer> playerBoard;

    public MessageChoosePersonalBonus(List<Integer> playerBoard)
    {
        this.playerBoard = playerBoard;
    }

    @Override
    public void execute(ClientController clientController) {

        //Set playerBoard

        clientController.getMainClientModel().changeMyState(ClientStateName.CHOOSE_PERSONAL_BONUS);

    }
}
