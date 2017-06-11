package it.polimi.ingsw.GC_06.Network.Server;

/**
 * Created by gabri on 09/06/2017.
 */
public class NetworkControllerSocket implements LoginHandler {
    private int timeOut;
    private int minPlayers;
    private int maxPlayers;

    public NetworkControllerSocket(int timeOut, int maxPlayers, int minPlayers) {
        this.timeOut = timeOut;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void handleLogin(String username) {
        
    }

    @Override
    public boolean checkLogin() {
        return false;
    }
}
