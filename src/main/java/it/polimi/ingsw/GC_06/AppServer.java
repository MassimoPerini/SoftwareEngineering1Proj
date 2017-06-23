package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Server.Message.ActionController;
import it.polimi.ingsw.GC_06.Server.Network.*;

import java.io.IOException;

/**
 * Created by massimo on 01/06/17.
 */
public class AppServer {

    public static void main( String[] args ) throws IOException {

        System.out.println("Server started...");

        ServerOrchestrator serverOrchestrator = new ServerOrchestrator();
        GameList.getInstance().setServerOrchestrator(serverOrchestrator);
        LoginHub loginHub = new LoginHub(serverOrchestrator);
        Server server = new SocketServer();
        server.setLoginHub(loginHub);
        serverOrchestrator.addServer(server);
        ActionController actionController = new ActionController();
        serverOrchestrator.addObserver(actionController);
        serverOrchestrator.start();

        System.out.println("Server ended...");

    }

}
