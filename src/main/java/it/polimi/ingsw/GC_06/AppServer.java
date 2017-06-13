package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Network.Server.SocketServer;

import java.io.IOException;

/**
 * Created by massimo on 01/06/17.
 */
public class AppServer {


    public static void main( String[] args ) throws IOException {
        System.out.println("Server started...");

        SocketServer socketServer = new SocketServer();



        System.out.println("Server ended...");

    }

}
