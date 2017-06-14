package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 13/06/17.
 */
public class ServerOrchestrator extends Observable implements Observer {

    @NotNull private final List<Server> servers;

    public ServerOrchestrator() {
        this.servers = new ArrayList<>();
    }

    public void addServer(Server server)
    {
        servers.add(server);
        server.addObserver(this);
    }

    public void startGame(Game game)
    {
        for (Server server : servers) {
            server.startGame(game);
        }
    }

    public void send(String playerId, Object o) throws IOException {
        for (Server server : servers) {
            if (server.isPlayerManaged(playerId))
            {
                server.sendMessageToPlayer(playerId, o);
                return;
            }
        }
    }

    public void send (int game, Object o) throws IOException {
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
