package it.polimi.ingsw.GC_06.Network.Server1;

/**
 * Created by giuseppe on 6/11/17.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by giuseppe on 6/6/17.
 */
public class MultiEchoServer {

    private int port;
    public MultiEchoServer(int port){
        this.port = port;
    }

    public void startServer(){
        ExecutorService executor = Executors.newCachedThreadPool(); // crea un pool di thread da usare

        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
        }catch(IOException e){
            System.err.println(e.getMessage());//porta non è disponibile
            return;
        }
        System.out.println("Server Ready");
        while(true){
            try{
                Socket socket = serverSocket.accept();
                executor.submit(new EchoServerClientHandler(socket));
            }catch(IOException e){
                break;
            }
        }
        executor.shutdown();
    }

    public static void main(String[] args) {
        MultiEchoServer echoServer = new MultiEchoServer(1337);
        echoServer.startServer();
    }
}