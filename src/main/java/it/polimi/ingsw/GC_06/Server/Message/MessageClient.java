package it.polimi.ingsw.GC_06.Server.Message;

import java.io.Serializable;

/**
 * Created by massimo on 11/06/17.
 */
public interface MessageClient extends Serializable {
    void execute();
    void setGame (int game);
    void setPlayer(String player);
    String getPlayer();
}
