package it.polimi.ingsw.GC_06.Network.Server;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 13/06/17.
 */
public class SocketServer extends Server implements Observer {

    @NotNull
    private List<ServerPlayerSocket> socketList;

    public SocketServer() {
        this.socketList = new ArrayList<>();
    }

    @Override
    public void start(){
        ExecutorService executor = Executors.newCachedThreadPool();
        SocketListener socketListener = new SocketListener(this);
        executor.submit(socketListener);
    }

    public synchronized void addPlayerSocket(@NotNull ServerPlayerSocket serverPlayerSocket)
    {
        this.socketList.add(serverPlayerSocket);
        serverPlayerSocket.addObserver(this);       //I'm the observer of the socket's client
    }

    @Override
    public synchronized void startGame(@NotNull Game game)
    {
        Map<String, Player> players = game.getGameStatus().getPlayers();

    }

    @Override
    public void update(Observable o, Object arg) {
        //Lo rilancio
    }
}
