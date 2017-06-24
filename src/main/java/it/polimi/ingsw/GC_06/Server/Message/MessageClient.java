package it.polimi.ingsw.GC_06.Server.Message;

/**
 * Created by massimo on 11/06/17.
 */
public interface MessageClient {
    void execute();
    void setGame (int game);
    void setPlayer(String player);
    String getPlayer();
}
