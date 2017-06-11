package it.polimi.ingsw.GC_06.Network.Server;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by gabri on 09/06/2017.
 */
public class NetworkControllerRMI  {
    private int timeOut;
    private int minPlayers;
    private int maxPlayers;

    public NetworkControllerRMI(int timeOut, int maxPlayers, int minPlayers) {
        this.timeOut = timeOut;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    //@Override
    public void handleLogin(String username) {

    }
}
