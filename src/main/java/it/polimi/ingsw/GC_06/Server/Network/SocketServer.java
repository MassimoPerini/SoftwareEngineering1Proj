package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 13/06/17
 * this class is the main socket server
 */
public class SocketServer extends Server implements Observer {

    private final List<ServerPlayerSocket> socketList;
    private final Map<String, ServerPlayerSocket> socketFromId;
    private final Map<Integer, List<ServerPlayerSocket>> socketsFromGame;

    public SocketServer() {
        this.socketList = new ArrayList<>();
        socketFromId = new HashMap<>();
        socketsFromGame = new HashMap<>();
    }

    /**
     * starts the server
     */
    @Override
    synchronized void start(){
        ExecutorService executor = Executors.newCachedThreadPool();
        SocketListener socketListener = new SocketListener(this, LoginHub.getInstance());
        executor.submit(socketListener);
    }

    /**
     * adds a player to the server
     * @param serverPlayerSocket player to be added
     */
    synchronized void addPlayerSocket(@NotNull ServerPlayerSocket serverPlayerSocket)
    {
        this.socketList.add(serverPlayerSocket);
        serverPlayerSocket.addObserver(this);       //I'm the observer of the socket's client
    }
    @Override
    synchronized boolean isPlayerManaged(@NotNull String player)
    {
        return socketFromId.get(player) != null;
    }

    /**
     *
     * @param player player that has to receive a comunication
     * @param o object to be passed to the player
     * @throws IOException
     */
    @Override
    synchronized void sendMessageToPlayer(@NotNull String player, @NotNull Object o) throws IOException
    {
        if (!isPlayerManaged(player))
            throw new IllegalArgumentException();
        socketFromId.get(player).send((MessageServer) o);       //TODO FIX IT
    }

    /**
     *
     * @param game game that has to receive a comunication
     * @param o object to be passed to the game
     * @throws IOException
     */
    @Override
    synchronized void sendMessageToGame(int game, @NotNull Object o) throws IOException {
        if (socketsFromGame.get(game)==null)
            return;

        List<ServerPlayerSocket> sockets = socketsFromGame.get(game);
        for (ServerPlayerSocket socket : sockets) {
            socket.send((MessageServer) o);     //TODO fix it
        }
    }

    /**
     *
     * @param player the player to be removed
     */
    @Override
    synchronized void remove(String player) {
        ServerPlayerSocket playerSocket = socketFromId.get(player);
        List<ServerPlayerSocket> serverPlayerSockets = socketsFromGame.get(playerSocket.getGame());
        serverPlayerSockets.remove(playerSocket);
        socketFromId.remove(player);
        playerSocket.finish();
    }


    /**
     * shuts down the server
     * @throws IOException
     */
    @Override
    synchronized void stop() throws IOException {
        for (ServerPlayerSocket serverPlayerSocket : socketList) {
            serverPlayerSocket.finish();
        }
    }

    @Override
    synchronized boolean startGame(Map<String, Player> players, int id)
    {
        boolean result = false;
        List <ServerPlayerSocket> gamePlayers = new ArrayList();
        for (ServerPlayerSocket serverPlayerSocket : socketList) {
            if (players.get(serverPlayerSocket.getPlayer()) != null)
            {
                socketFromId.put(serverPlayerSocket.getPlayer(), serverPlayerSocket);
                gamePlayers.add(serverPlayerSocket);
                serverPlayerSocket.setGame(id);
                result = true;
            }
        }
        if (result)
            socketsFromGame.put(id, gamePlayers);
        return result;
    }

    @Override
    synchronized public void update(Observable o, @NotNull Object arg) {
        //A player called me
        setChanged();
        // trigger notification
        notifyObservers(arg);
    }
}
