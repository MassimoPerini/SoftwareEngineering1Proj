package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 15/06/17.
 */
public class MessageClearBoard implements MessageServer {
    @Override
    public void execute(MainClientModel mainClientModel) {
        mainClientModel.getClientBoardGame().clearAllFamilyMembers();
    }
}
