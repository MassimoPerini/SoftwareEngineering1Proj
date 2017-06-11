package it.polimi.ingsw.GC_06.Network.Server1;

/**
 * Created by giuseppe on 6/11/17.
 */

import it.polimi.ingsw.GC_06.model.State.Game;

import java.io.*;
import java.net.Socket;

/**
 * Created by giuseppe on 6/7/17.
 */
public class EchoServerClientHandler implements Runnable, LoginManager {


    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    int delay = 1000;

    public EchoServerClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run(){

        try{

            // apre un buffer readere per prendere i dati in arrivo dal client
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String username = input.readLine();
            System.out.println("Username " + username);
            String password = input.readLine();
            System.out.println("Passoword  " + password);

            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            // apriamo comunque il canale di comunicazione con il client

            doLogin(username);


        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void doLogin(String username) {
        try{
            Game.getInstance().addPlayer(username);
            output.println("login done");
        }catch(IllegalStateException e){
            output.println(e.getMessage());

        }
        output.flush();
    }
}
