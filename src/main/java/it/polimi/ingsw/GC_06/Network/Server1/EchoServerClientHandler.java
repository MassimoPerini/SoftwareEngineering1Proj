package it.polimi.ingsw.GC_06.Network.Server1;

/**
 * Created by giuseppe on 6/11/17.
 */

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by giuseppe on 6/7/17.
 */
public class EchoServerClientHandler implements Runnable, LoginManager {


    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    int delay = 10000;

    public EchoServerClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run(){

        try{

            // apre un buffer readere per prendere i dati in arrivo dal client
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String username = input.readLine();
            System.out.println("Username " + username);
            /**String password = input.readLine();
            System.out.println("Passoword  " + password);*/

            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));


            /** effettuata questa operazione avremo i nostri giocatori loggati ed il gioco iniziato*/
            doLogin(username);

            /** adesso vorremmo inviare al giocatore tutte le sue informazioni*/
            statistics(username);

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void doLogin(String username) {
        try{
            Game.getInstance().addPlayer(username);
            output.println("login done");
            if(Game.getInstance().getPlayerNumber()==2){
                 Timer timer = new Timer();
                 timer.schedule(new TimerTask() {
                     @Override
                     public void run() {
                         System.out.println("creo un nuovo gioco");
                         Game.getInstance().start();
                     }
                 },delay);
            }
        }catch(IllegalStateException e){
            output.println(e.getMessage());

        }
        output.flush();
    }

    @Override
    public void statistics(String username) {

        Player player = Game.getInstance().getGameStatus().getPlayers().get(username);
        output.println(player.toString());
        output.flush();
    }
}
