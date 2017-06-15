package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Server.Network.LoginHub;
import it.polimi.ingsw.GC_06.Server.Network.Server;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.Server.Network.SocketServer;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by massimo on 01/06/17.
 */
public class AppServer {


    public static void main( String[] args ) throws IOException {
        System.out.println("Server started...");

        ServerOrchestrator serverOrchestrator = new ServerOrchestrator();
        LoginHub loginHub = new LoginHub(serverOrchestrator);
        Server server = new SocketServer();
        server.setLoginHub(loginHub);
        serverOrchestrator.addServer(server);
        serverOrchestrator.start();

        System.out.println("Server ended...");

    }

}
