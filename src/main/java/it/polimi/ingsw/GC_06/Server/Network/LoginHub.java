package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;

import java.io.IOException;
import java.util.*;

/**
 * Created by massimo on 13/06/17.
 */
public class LoginHub {

    private List<String> totPlayers = new ArrayList<>(); /** tutti i giocatori iscritti alle partire */
    private List<String> loggedPlayers = new ArrayList<>();
    private Trash playerTrash = new Trash();
    private int delay  = 1000*15;//Integer.parseInt(Setting.getInstance().getProperty("timer"));
    private Timer timer = new Timer(true);
    private int id = 0;

    public static LoginHub instance = new LoginHub();

    private ServerOrchestrator serverOrchestrator;


    private LoginHub() {
        super();
    }

    public void setServerOrchestrator(ServerOrchestrator serverOrchestrator) {
        this.serverOrchestrator = serverOrchestrator;
        GameList.getInstance().setServerOrchestrator(serverOrchestrator);
    }

    public static LoginHub getInstance() {
        return instance;
    }
    /**
     * @param username
     *
     */
    public synchronized void loginHandler(String username) {

        if (searchTrash(username)) {

            /** il mio loginHub fa da controller perchè sarà lui ad andare a rimettere il mio player esattamente dove doveva essere*/

            restoreInTheGame(username,playerTrash.getGameID(username));
            totPlayers.add(username);
        }
        else{
            /** chiamo immediatamente il login */
            try {
                addUser(username);
            }
            catch (Exception e)
            {}
        }

    }

    /** se viene chiamata l'eccezione perchè il player si è disconnesso dobbiamo andarlo a cancellare dalla
     * lista del gioco in cui stava giocando
     * @param username
     */
    public void manageLogOut(String username){

        totPlayers.remove(username);
        /** qui mi fai ritornare il game id così io lo rimuovo dalla lista sul game */
        int gameID = GameList.getInstance().getGame(username);
        playerTrash.add(username,gameID);
        /** abbiamo rimosso il player dalla partita*/
    //    GameList.getInstance().getGameId(gameID).remove(username);
        GameList.getInstance().remove(gameID,username);
    }

    public void addUser(String user) throws IllegalArgumentException, IOException {


        /** qui faccio il controllo anche sul fatto che un giocatore non può essere registrato su più partite */

        System.out.println("LoginHub: tentativo di loggare l'utente: "+user);

        //TODO secondo me non serve
        /**if(!access(user)) {
            throw new IllegalStateException();
        }*/
            System.out.println("ti sto loggando");

            loggedPlayers.add(user);

            System.out.println("this is the size of logged player " + loggedPlayers.size());

        if(loggedPlayers.size() == 10 /**Integer.parseInt((Setting.getInstance().getProperty("min_playes")))*/){

            timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            Game game = new Game(id);
                            ControllerGame controllerGame = new ControllerGame(game, serverOrchestrator, id);
                            uploadPlayers(game, loggedPlayers);
                            System.out.println("Sto creando il gioco");
                            GameList.getInstance().add(game, loggedPlayers);
                            controllerGame.start();
                            //Il gioco lo avvia lui
                            id++;
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                },delay);

                //this.myTimer(game);
            }
            if (loggedPlayers.size() == 2 /** Integer.parseInt(Setting.getInstance().getProperty("max_player"))*/) {
                Game game = new Game(id);
                ControllerGame controllerGame = new ControllerGame(game,serverOrchestrator,id);
                timer.cancel();
                uploadPlayers(game,loggedPlayers);
                GameList.getInstance().add(game,loggedPlayers);
                id++;
                controllerGame.start();     //Il gioco lo avvia lui

                /** si salva per ogni gioco l'id dei partecipanti -> Mappa <username/Socket>*/
                   // serverOrchestrator.startGame(game);
                loggedPlayers = new ArrayList<>();
            }

    }

    public synchronized boolean access(String user) {

        /** la lista contiene l'elenco di tutti i giocatori effettivamente registrati a tutti i giochi  quindi */
        if(!totPlayers.contains(user)){
            totPlayers.add(user);
            return true;
        }
        return false;
    }


    /**
     *
     * @param username
     * @return
     */

    public boolean searchTrash(String username){
       return  playerTrash.search(username);
    }

    /**
     *
     * @param username
     * @param gameID
     *
     * it gets infos from the trash in order to find the right game where the player was before logging out
     *
     */

    public void restoreInTheGame(String username,int gameID){
        Game game = GameList.getInstance().getGameId(gameID);
        try{
            game.addPlayer(username);

        }catch (NullPointerException e){
            e.getStackTrace();
        }

    }

    private void uploadPlayers(Game game, List<String> players){
        for(String username : players){
            game.addPlayer(username);
        }
        players = new LinkedList<>();
    }

    public void myTimer(Game game){

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("create the game");
          //      game.start();
                timer.cancel();
            }
        };

        timer = new Timer();
        timer.schedule(timerTask,delay);
    }







    public List<String> getTotPlayers() {
        return totPlayers;
    }

    public List<String> getLoggedPlayers() {
        return loggedPlayers;
    }
}
