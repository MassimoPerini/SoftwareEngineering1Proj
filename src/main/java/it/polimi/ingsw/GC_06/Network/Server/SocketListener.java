package it.polimi.ingsw.GC_06.Network.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 13/06/17.
 */
public class SocketListener implements Runnable {

    private static final int PORT = 1337;
    private SocketServer socketServer;

    public SocketListener (SocketServer socketServer)
    {
        this.socketServer = socketServer;
    }

    @Override
    public void run() {
        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            //creats the socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("SERVER SOCKET READY ON PORT" + PORT);

            while (true) {
                //Waits for a new client to connect
                Socket inputSocket = serverSocket.accept();
                // creates the view (server side) associated with the new client
                ServerPlayerSocket serverPlayerSocket = new ServerPlayerSocket(inputSocket);
                // a new thread handle the connection with the view
                executor.submit(serverPlayerSocket);

                socketServer.addPlayerSocket(serverPlayerSocket);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
