package it.polimi.ingsw.GC_06.Network.Client1;

/**
 * Created by giuseppe on 6/11/17.
 */
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by giuseppe on 6/6/17.
 */
public class LineClient{

    private String ip;
    private int port;
    private String username;
    private String password;

    public LineClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public static void main(String[] args) {



        LineClient client = new LineClient("127.0.0.1",1337);
        try{
            client.startClient();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void startClient() throws IOException{
        /**Socket socket = new Socket(ip,port);
         System.out.println("Connection established");
         Scanner socketIn = new Scanner(socket.getInputStream());
         PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
         System.out.println("userId");
         Scanner stdin = new Scanner(System.in); /** questo è quello che inserisce l'utente --> che noi vogliamo al momento essere il login
         System.out.println("pass");
         Scanner stdin1 = new Scanner(System.in);
         try{
         while(true){
         String inputLine = stdin.nextLine();
         String inputLine1 = stdin1.nextLine();
         socketOut.println(inputLine);
         socketOut.flush();

         String socketLine = socketIn.nextLine(); /** quello che mi arriva dal server
         System.out.println(socketLine);/** stampo quello che mi è arrivato dal server
         }
         }
         catch(NoSuchElementException e){
         System.out.println("connection closed");
         }
         finally{
         stdin.close();
         socketIn.close();
         socketOut.close();
         socket.close();
         }*/

        Socket socket;
        PrintWriter output;
        BufferedReader read;
        Scanner socketIn = new Scanner(System.in);

        socket = new Socket(ip,port);
        output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        String username = JOptionPane.showInputDialog(null,"enter username");
        output.println(username);

        String pass = JOptionPane.showInputDialog(null,"enter pass");
        output.println(pass);

        output.flush();

        //salviamo quello che arriva dal server
        read = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //leggiamo quello che è nel buffer

        String response = read.readLine();
        System.out.println("This is the response from the Server: " + response);

        JOptionPane.showMessageDialog(null,response);
        /**String socketLine = socketIn.nextLine();
         System.out.println(socketLine);*/

        /**try{
         TimeUnit.SECONDS.sleep(10);
         }
         catch(Exception e){

         }*/


    }




}
