package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

/**
 * Created by massimo on 26/06/17.
 * this class is the RMI server of the game
 */
public class RMIServer extends Server implements Observer {

    private final Map<String, ServerPlayerRMIHandler> playerRMI;
    private final Map<Integer, List<ServerPlayerRMIHandler>> playerFromGame;


    public RMIServer ()
    {
        playerRMI = new HashMap<>();
        playerFromGame = new HashMap<>();
    }

    /**
     * this method starts the server, who begin listening for connections
     */
    @Override
    synchronized void start() {

        try {
            Registry registry = LocateRegistry.createRegistry(5299);
            LoginListener loginListener = new LoginListener(this);
            registry.bind("lorenzo_magnifico", loginListener);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        //Fai partire il registry

    }

    /**
     * adds a player to the server
     * @param serverPlayerRMIClient client performing login
     * @param player player associated to the client
     */
    synchronized void addPlayerRMI(ServerPlayerRMIHandler serverPlayerRMIClient, String player)
    {
        this.playerRMI.put(player, serverPlayerRMIClient);
        serverPlayerRMIClient.addObserver(this);
    }

    /**
     *
     * @param players player
     * @param id username of the player
     * @return
     */
    @Override
    synchronized boolean startGame(Map<String, Player> players, int id) {

        boolean result = false;
        List<ServerPlayerRMIHandler> gamePlayers = new ArrayList();

        for (String player : players.keySet()) {
            ServerPlayerRMIHandler serverPlayerRMIClient = playerRMI.get(player);
            if (serverPlayerRMIClient !=null)
            {
                serverPlayerRMIClient.setGame(id);
                gamePlayers.add(serverPlayerRMIClient);
                result = true;
            }
        }

        if (result)
        {
            playerFromGame.put(id, gamePlayers);
        }

        return result;
    }

    @Override
    synchronized boolean isPlayerManaged(String player) {
        return playerRMI.get(player)!=null;
    }

    /**
     *
     * @param player player who has to receive a comunication
     * @param o the object to be passed to the player
     * @throws IOException
     */
    @Override
    synchronized void sendMessageToPlayer(String player, Object o) throws IOException {

        MessageServer messageServer = (MessageServer) o;
        ServerPlayerRMIHandler serverPlayerRMIClient = playerRMI.get(player);
        if (serverPlayerRMIClient == null)
        {
            throw new IllegalArgumentException();
        }
        serverPlayerRMIClient.send(messageServer);
    }

    /**
     *
     * @param game the game that has to receive a comunication
     * @param o the object to be passed to the game
     * @throws IOException
     */
    @Override
    synchronized void sendMessageToGame(int game, Object o) throws IOException {

        MessageServer messageServer = (MessageServer) o;
        List<ServerPlayerRMIHandler> players = playerFromGame.get(game);
        if (players==null)
            return;
        for (ServerPlayerRMIHandler player : players) {
            player.send(messageServer);
        }
    }

    /**
     * removes a player rom the server
     * @param player player to be removed
     */
    @Override
    void remove(String player) {
        ServerPlayerRMIHandler playerRMIHandler = playerRMI.get(player);
        List<ServerPlayerRMIHandler> serverPlayers = playerFromGame.get(playerRMIHandler.getGame());
        serverPlayers.remove(playerRMIHandler);
        playerRMI.remove(player);
        playerRMIHandler.finish();
    }

    /**
     * shuts down the server
     * @throws IOException
     */
    @Override
    synchronized void stop() throws IOException {

        for (String s : playerRMI.keySet()) {
            playerRMI.get(s).finish();
        }
    }

    @Override
    synchronized boolean addUserToGame(String user, int game) {
        ServerPlayerRMIHandler serverPlayerRMIHandler = playerRMI.get(user);
        if (serverPlayerRMIHandler!=null) {
            playerFromGame.get(game).add(serverPlayerRMIHandler);
            return true;
        }
        return false;
    }

    @Override
    synchronized public void update(Observable o, Object arg) {
        try {
            MessageClient messageClient = (MessageClient) arg;
            System.out.println("INVOKING SETCHANGED");
            setChanged();
            System.out.println("ERROE");
            notifyObservers(messageClient);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
