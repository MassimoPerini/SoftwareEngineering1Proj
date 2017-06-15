package it.polimi.ingsw.GC_06.Server.Network;

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
    private LoginHub loginHub;

    public SocketListener (SocketServer socketServer, LoginHub loginHub)
    {
        this.socketServer = socketServer;
        this.loginHub = loginHub;
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
                System.out.println("New connection on Socket");
                ServerPlayerSocket serverPlayerSocket = new ServerPlayerSocket(inputSocket, loginHub);
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
