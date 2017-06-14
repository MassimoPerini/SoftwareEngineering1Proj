package it.polimi.ingsw.GC_06.ServerTry;

/**
 * Created by giuseppe on 6/11/17.
 */


import it.polimi.ingsw.GC_06.model.Controller.*;

import java.io.*;
import java.net.Socket;

/**
 * Created by giuseppe on 6/7/17.
 */
public class EchoServerClientHandler implements Runnable, LoginManager {

    private String userId;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    int delay = 10000;
    private ModelController modelController;

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

            userId = username;
            output.println("The server registered you with your id: " + userId);
            output.flush();

            /** effettuata questa operazione avremo i nostri giocatori loggati ed il gioco iniziato*/
            doLogin(username);



            /** adesso vorremmo inviare al giocatore tutte le sue informazioni*/
            statistics(username);

            /** dobbiamo impedire al client di effettuare azioni in questa fase se non ci sono due giocatori*/



        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void doLogin(String username) {
       /** try{
            LoginHub.getInstance().fillHub(username);
        }catch(IllegalStateException e){
            output.println(e.getMessage());
        }
        output.flush();*/
    }

    @Override
    public void statistics(String username) {
/**
        Player player = Game.getInstance().getGameStatus().getPlayers().get(username);
        //TODO da fare con json
        output.println(player.toString());
        output.flush();*/
    }

    /** qui dovrà iniziare la gestione delle azioni */
    /** quello che pensavo di fare io era di chiamare qui la classe di handling del model con pattern strategy  */
    //TODO qui deserializza la stringa e tira fuori il messaggio e l'object
    /**public void callToModel() {
        //simulo di avere gia il messaggio e l'object
        Object objectFromSocket = new Object();
        String messageFromSocket = new String();
        switch (messageFromSocket) {
            case "messaggio azione su torre" :
                modelController = new ActionOnTowerController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio azione su prodHarv" :
                modelController = new ActionOnProdHarvController();
                modelController.ActOnModel(objectFromSocket,userId);
            case "messaggio askUserSelector" :
                modelController = new AskUserSelectorController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio endTurn" :
                modelController = new EndTurnActionController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio executeEffects" :
                modelController = new ExecuteEffectsController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio HarvestCardSelector" :
                modelController = new HarvestCardSelectorController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio Paycard" :
                modelController = new PayCardController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio PickCard" :
                modelController = new PickCardController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio PowerUpFamilyMember" :
                modelController = new PowerUpFamilyMemberController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio StartProdHarv" :
                modelController = new StartProdHarvController();
                modelController.ActOnModel(objectFromSocket, userId);
            case "messaggio VariateResource" :
                modelController = new VariateResourceActionController();
                modelController.ActOnModel(objectFromSocket, userId);
        }
    }*/
}
