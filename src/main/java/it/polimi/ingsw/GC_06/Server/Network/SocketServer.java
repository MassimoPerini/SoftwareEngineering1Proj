package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
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
    private final List<ServerPlayerSocket> socketList;
    @NotNull private final Map<String, ServerPlayerSocket> socketFromId;
    @NotNull private final Map<Integer, List<ServerPlayerSocket>> socketsFromGame;

    public SocketServer() {
        this.socketList = new ArrayList<>();
        socketFromId = new HashMap<>();
        socketsFromGame = new HashMap<>();
    }

    @Override
    void start(){
        ExecutorService executor = Executors.newCachedThreadPool();
        SocketListener socketListener = new SocketListener(this, LoginHub.getInstance());
        executor.submit(socketListener);
    }

    synchronized void addPlayerSocket(@NotNull ServerPlayerSocket serverPlayerSocket)
    {
        this.socketList.add(serverPlayerSocket);
        serverPlayerSocket.addObserver(this);       //I'm the observer of the socket's client
    }
    @Override
    boolean isPlayerManaged(@NotNull String player)
    {
        return socketFromId.get(player) != null;
    }

    @Override
    void sendMessageToPlayer(@NotNull String player, @NotNull Object o) throws IOException
    {
        if (!isPlayerManaged(player))
            throw new IllegalArgumentException();
        socketFromId.get(player).send((MessageServer) o);       //TODO FIX IT
    }

    @Override
    void sendMessageToGame(int game, @NotNull Object o) throws IOException {
        if (socketsFromGame.get(game)==null)
            return;

        List<ServerPlayerSocket> sockets = socketsFromGame.get(game);
        for (ServerPlayerSocket socket : sockets) {
            socket.send((MessageServer) o);     //TODO fix it
        }
    }


    @Override
    void stop() throws IOException {
        for (ServerPlayerSocket serverPlayerSocket : socketList) {
            serverPlayerSocket.finish();
        }
    }

    @Override
    synchronized boolean startGame(@NotNull Map<String, Player> players, int id)
    {
        boolean result = false;
        List <ServerPlayerSocket> gamePlayers = new ArrayList();
        for (ServerPlayerSocket serverPlayerSocket : socketList) {
            if (players.get(serverPlayerSocket.getPlayer()) != null)
            {
                socketFromId.put(serverPlayerSocket.getPlayer(), serverPlayerSocket);
                gamePlayers.add(serverPlayerSocket);
                result = true;
            }
        }
        if (result)
            socketsFromGame.put(id, gamePlayers);
        return result;
    }

    @Override
    public void update(Observable o, @NotNull Object arg) {
        //A player called me
        setChanged();
        // trigger notification
        notifyObservers(arg);
    }
}
