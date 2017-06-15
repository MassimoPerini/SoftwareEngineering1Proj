package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by massimo on 13/06/17.
 */
public class LoginHub {

    private List<String> totPlayers = new ArrayList<>(); /** tutti i giocatori iscritti alle partire */
    private List<String> loggedPlayers = new ArrayList<>();
    private Trash playerTrash = new Trash();
    private int delay  = Integer.parseInt(Setting.getInstance().getProperty("timer"));
    private Game game;
    Timer timer = new Timer();

    //public static LoginHub instance = new LoginHub();

    private final ServerOrchestrator serverOrchestrator;


    public LoginHub(@NotNull ServerOrchestrator serverOrchestrator) {
        this.serverOrchestrator = serverOrchestrator;
    }

   /*public static LoginHub getInstance() {
        return instance;
    }*/
    /**
     * @param username
     *
     */
    public void loginHandler(String username) throws IOException {

        if (searchTrash(username)) {

            /** il mio loginHub fa da controller perchè sarà lui ad andare a rimettere il mio player esattamente dove doveva essere*/

            restoreInTheGame(username,playerTrash.getGameID(username));
            totPlayers.add(username);
        }
        else{
            /** chiamo immediatamente il login */

            addUser(username);
        }

    }

    /** se viene chiamata l'eccezione perchè il player si è disconnesso dobbiamo andarlo a cancellare dalla
     * lista del gioco in cui stava giocando
     * @param username
     */
    public void ManageLogOut(String username){

        totPlayers.remove(username);
        /** qui mi fai ritornare il game id così io lo rimuovo dalla lista sul game */
        int gameID = GameList.getInstance().getGame(username);
        playerTrash.add(username,gameID);
        /** abbiamo rimosso il player dalla partita*/
        GameList.getInstance().getGameId(gameID).remove(username);
        GameList.getInstance().remove(gameID,username);
    }

    public void addUser(String user) throws IllegalArgumentException, IOException {


        /** qui faccio il controllo anche sul fatto che un giocatore non può essere registrato su più partite */



        if(access(user)) {

            totPlayers.add(user);
            loggedPlayers.add(user);

            if(loggedPlayers.size() == 2 /**Integer.parseInt((Setting.getInstance().getProperty("min_playes")))*/){
                game = new Game();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                            uploadPlayers(game,loggedPlayers);
                            game.start();
                            GameList.getInstance().add(game,loggedPlayers);
                    }
                },delay);
            }
            if (loggedPlayers.size() == 4 /** Integer.parseInt(Setting.getInstance().getProperty("max_player"))*/) {
                timer.cancel();
                uploadPlayers(game,loggedPlayers);
                game.start();
                GameList.getInstance().add(game,loggedPlayers);

                /** si salva per ogni gioco l'id dei partecipanti -> Mappa <username/Socket>*/
                   // serverOrchestrator.startGame(game);
                loggedPlayers = new ArrayList<>();
            }

        }
    }

    public boolean access(String user) {

        /** la lista contiene l'elenco di tutti i giocatori effettivamente registrati a tutti i giochi  quindi */

        return !totPlayers.contains(user);
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


    public List<String> getTotPlayers() {
        return totPlayers;
    }

    public List<String> getLoggedPlayers() {
        return loggedPlayers;
    }
}
