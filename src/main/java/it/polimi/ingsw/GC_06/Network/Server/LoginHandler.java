package it.polimi.ingsw.GC_06.Network.Server;

/**
 * Created by gabri on 09/06/2017.
 */
public interface LoginHandler {
    void handleLogin(String username);
    boolean checkLogin();
}
