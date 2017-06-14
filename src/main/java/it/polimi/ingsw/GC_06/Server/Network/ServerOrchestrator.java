package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;

/**
 * Created by massimo on 13/06/17.
 */
public class ServerOrchestrator extends Observable implements Observer {

    @NotNull private final List<Server> servers;
    @NotNull private final Map<String, Server> serverByString;
    @NotNull private final Map<Integer, List<Server>> serverByGame;


    public ServerOrchestrator() {
        this.servers = new ArrayList<>();
        this.serverByString = new HashMap<>();
        this.serverByGame = new HashMap<>();
    }

    public void addServer(Server server)
    {
        servers.add(server);
        server.addObserver(this);
    }

    public void startGame(Map<String, Player> players, int id)
    {
        List<Server> servers = new LinkedList<>();
        for (Server server : servers) {
            if (server.startGame(players, id))
            {
                servers.add(server);
                for (String s : players.keySet()) {
                    if (server.isPlayerManaged(s))
                    {
                        serverByString.put(s, server);
                    }
                }
            }
        }
        this.serverByGame.put(id, servers);
    }

    public void send(String playerId, Object o) throws IOException {
        Server server = serverByString.get(playerId);
        server.sendMessageToPlayer(playerId, o);
    }

    public void send (int game, Object o) throws IOException {
        List<Server> servers = serverByGame.get(game);
        for (Server server : servers) {
            server.sendMessageToGame(game, o);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //A server called me;
        setChanged();
        // trigger notification
        notifyObservers(arg);
    }
}
