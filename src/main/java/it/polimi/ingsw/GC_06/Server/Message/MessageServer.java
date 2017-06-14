package it.polimi.ingsw.GC_06.Server.Message;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;

/**
 * Created by massimo on 12/06/17.
 */
public interface MessageServer {

    void execute(MainClientModel mainClientModel);
}
