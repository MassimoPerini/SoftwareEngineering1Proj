package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessagePlayerDisconnected;
import it.polimi.ingsw.GC_06.model.Action.Actions.EndTurn;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.*;

/**
 * Created by massimo on 13/06/17.
 * this class is an entry point for all the connections of the networked game, managing all connections from clients
 */
public class LoginHub {

    private List<String> totPlayers = new ArrayList<>(); /** tutti i giocatori iscritti alle partire */
    private List<String> loggedPlayers = new ArrayList<>();
    private Trash playerTrash = new Trash();
    private int delay  = Integer.parseInt(Setting.getInstance().getProperty("timer_login"));
    private Timer timer = new Timer(true);
    private int id = 0;
    private List<String> alreadyNotified = new LinkedList<>();

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
    public void loginHandler(String username) {

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
            {e.printStackTrace();}
        }

    }

    /** se viene chiamata l'eccezione perchè il player si è disconnesso dobbiamo andarlo a cancellare dalla
     * lista del gioco in cui stava giocando
     * @param username
     */
    public void manageLogOut(String username){
        if (playerTrash.isInTrash(username))
        {
            return;
        }
    //    totPlayers.remove(username);
        /** qui mi fai ritornare il game id così io lo rimuovo dalla lista sul game */
        int gameID = GameList.getInstance().getGame(username);
    //    playerTrash.add(username,gameID);
        /** abbiamo rimosso il player dalla partita*/
    //    GameList.getInstance().getGameId(gameID).remove(username);
        Game currGame = GameList.getInstance().getGameId(gameID);
        MessagePlayerDisconnected messagePlayerDisconnected = new MessagePlayerDisconnected(username);
        for (Player player : currGame.getGameStatus().getPlayers().values()) {
            if (player.isConnected())
            {
                serverOrchestrator.notifyUser(player.getPLAYER_ID(), messagePlayerDisconnected);
            }
        }
        alreadyNotified.add(username);
        currGame.getGameStatus().getPlayers().get(username).setConnected(false);
        //    GameList.getInstance().remove(gameID,username);
        if (currGame.getCurrentPlayer().getPLAYER_ID().equals(username)) {
            EndTurn endTurn = new EndTurn(GameList.getInstance().getGameId(gameID));
            endTurn.execute();
        }
    }

    public void removeAlreadyNotified(String user)
    {
        alreadyNotified.remove(user);
    }

    public void manageDisconnection(String username)
    {
        if (playerTrash.isInTrash(username))
        {
            return;
        }
        System.out.println("Disconnecting "+username);
        int gameID = GameList.getInstance().getGame(username);
        Game actGame = GameList.getInstance().getGameId(gameID);
        actGame.getGameStatus().getPlayers().get(username).setConnected(false);
        playerTrash.add(username,gameID);
        //     GameList.getInstance().remove(gameID,username);
        serverOrchestrator.remove(username, gameID);

        if (!alreadyNotified.contains(username))
        {
            MessagePlayerDisconnected messagePlayerDisconnected = new MessagePlayerDisconnected(username);
            for (Player player : actGame.getGameStatus().getPlayers().values()) {
                if (player.isConnected())
                {
                    serverOrchestrator.notifyUser(player.getPLAYER_ID(), messagePlayerDisconnected);
                }
            }
        }
        if (actGame.getCurrentPlayer().getPLAYER_ID().equals(username))
        {
            EndTurn endTurn = new EndTurn(GameList.getInstance().getGameId(gameID));
            endTurn.execute();
        }
        GameList.getInstance().getGameMap().get(actGame).remove(username);
        totPlayers.remove(username);
        removeAlreadyNotified(username);
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
        System.out.println("Restoring...");
        Game game = GameList.getInstance().getGameId(gameID);
        try{
            serverOrchestrator.addUserToGame(username, gameID);
            System.out.println("Added user to server orchestrator");
            totPlayers.add(username);
            playerTrash.remove(username);

            GameList.getInstance().getGameMap().get(GameList.getInstance().getGameId(gameID)).add(username);
            Player player = game.getGameStatus().getPlayers().get(username);
            player.setConnected(true);
            ControllerJoinAgain controllerJoinAgain = new ControllerJoinAgain();
            controllerJoinAgain.execute(serverOrchestrator, username, game);

        }catch (NullPointerException e){
            e.getStackTrace();
        }

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

        if(loggedPlayers.size() == Integer.parseInt((Setting.getInstance().getProperty("min_players")))){

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
            if (loggedPlayers.size() ==  Integer.parseInt(Setting.getInstance().getProperty("max_players"))) {
                Game game = new Game(id);
                ControllerGame controllerGame = new ControllerGame(game,serverOrchestrator,id);
                timer.cancel();
                uploadPlayers(game,loggedPlayers);
                GameList.getInstance().add(game,loggedPlayers);
                id++;
                loggedPlayers = new ArrayList<>();
                controllerGame.start();     //Il gioco lo avvia lui

                /** si salva per ogni gioco l'id dei partecipanti -> Mappa <username/Socket>*/
                   // serverOrchestrator.startGame(game);
            }

    }

    public boolean access(String user) {

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


    private void uploadPlayers(Game game, List<String> players){
        for(String username : players){
            game.addPlayer(username);
        }
        players = new LinkedList<>();
    }

    public void setPlayerTrash(Trash playerTrash) {
        this.playerTrash = playerTrash;
    }

    public Trash getPlayerTrash() {
        return playerTrash;
    }

    /**
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
    }*/







    public List<String> getTotPlayers() {
        return totPlayers;
    }

    public List<String> getLoggedPlayers() {
        return loggedPlayers;
    }
}
