package it.polimi.ingsw.GC_06.Network.Server;

import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 13/06/17.
 */
public class ServerOrchestrator extends Observable implements Observer {

    List<Server> servers;

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

    @Override
    public void update(Observable o, Object arg) {
        //A server called me;
    }
}
