package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Server.Network.SocketServer;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;

import java.io.IOException;

/**
 * Created by massimo on 01/06/17.
 */
public class AppServer {


    public static void main( String[] args ) throws IOException {
        System.out.println("Server started...");

        SocketServer socketServer = new SocketServer();

        FileLoader.getFileLoader().writeBoard();

        System.out.println("Server ended...");

    }

}
