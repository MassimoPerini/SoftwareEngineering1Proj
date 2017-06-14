package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by massimo on 13/06/17.
 */
public class LoginHub {

    private List<String> totPlayers = new ArrayList<>(); /** tutti i giocatori iscritti alle partire */
    private List<String> loggedPlayers = new ArrayList<>();


    public static LoginHub instance = new LoginHub();

    ServerOrchestrator serverOrchestrator = new ServerOrchestrator();

    private LoginHub() {

    }

    public static LoginHub getInstance() {
        return instance;
    }

    public void addUser(String user) throws IllegalArgumentException, IOException {

        /** qui faccio il controllo anche sul fatto che un giocatore non può essere registrato su più partite */


        if(access(user)) {

            totPlayers.add(user);
            loggedPlayers.add(user);


            if (loggedPlayers.size() == 4 /** Integer.parseInt(Setting.getInstance().getProperty("max_player"))*/) {
                Game game = new Game();
                for (String username : loggedPlayers) {
                    game.addPlayer(username);
                    game.start();
                    /** si salva per ogni gioco l'id dei partecipanti -> Mappa <username/Socket>*/
                    serverOrchestrator.startGame(game);
                }
                loggedPlayers = new ArrayList<>();
            }

        }
    }

    public boolean access(String user) {

        /** la lista contiene l'elenco di tutti i giocatori effettivamente registrati a tutti i giochi  quindi */

        if (totPlayers.contains(user)) {
            return false;
        }
        return true;
    }

    public void setServerOrchestrator(ServerOrchestrator serverOrchestrator) {
        this.serverOrchestrator = serverOrchestrator;
    }

    public List<String> getTotPlayers() {
        return totPlayers;
    }

    public List<String> getLoggedPlayers() {
        return loggedPlayers;
    }
}
