package it.polimi.ingsw.GC_06.Network.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by gabri on 10/06/2017.
 */
public class ClientServerSocket {
    private Socket socket;
    private Scanner inFromPresenters;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    /*la classe ha un socket (con canale di input e canale di output) che si occupa della comunicazione con il server,
    ma è anche dotata di uno scanner che riceve in locale le stringhe passate dal presenter     */

    public ClientServerSocket(Socket socket) {
        this.socket = socket;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("BufferReader fallito con errore " + e);
        }
        try {
            this.printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        } catch (IOException e) {
            System.err.println("PrintWriter fallito con errore " + e);
        }
        //lo scanner viene istanziato per icevere l'input del main presenter
        //this.inFromPresenters = new Scanner(main presenter)
    }

    public String getFromServerSocket() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //a questo punto bisogna solo fare in modo di collegare al canale del socket che va verso il server lo Scanner istanziato




}
