package it.polimi.ingsw.GC_06.Network.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 13/06/17.
 */

public class ClientSocket {

    private final static int PORT = 29999;
    private final static String IP = "127.0.0.1";

    public void startClient() throws IOException {

        Socket socket = new Socket(IP, PORT);

        System.out.println("Connection created");

        ExecutorService executor = Executors.newFixedThreadPool(1);

        ClientOutputSocket clientOutputSocket = new ClientOutputSocket(new ObjectOutputStream(socket.getOutputStream()));

        executor.submit(new ClientInputSocket(new ObjectInputStream(socket.getInputStream())));
}

}
