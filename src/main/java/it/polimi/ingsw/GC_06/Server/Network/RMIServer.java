package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 26/06/17.
 */
public class RMIServer extends Server implements Observer {

    private final ExecutorService executorService;
    private final Map<String, ServerPlayerRMIHandler> playerRMI;
    private final Map<Integer, List<ServerPlayerRMIHandler>> playerFromGame;


    public RMIServer ()
    {
        executorService = Executors.newCachedThreadPool();
        playerRMI = new HashMap<>();
        playerFromGame = new HashMap<>();
    }

    @Override
    void start() {

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

    void addPlayerRMI(ServerPlayerRMIHandler serverPlayerRMIClient, String player)
    {
        this.playerRMI.put(player, serverPlayerRMIClient);
        serverPlayerRMIClient.addObserver(this);
    }

    @Override
    boolean startGame(Map<String, Player> players, int id) {

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
    boolean isPlayerManaged(String player) {
        return playerRMI.get(player)!=null;
    }

    @Override
    void sendMessageToPlayer(String player, Object o) throws IOException {

        MessageServer messageServer = (MessageServer) o;
        ServerPlayerRMIHandler serverPlayerRMIClient = playerRMI.get(player);
        if (serverPlayerRMIClient == null)
        {
            throw new IllegalArgumentException();
        }
        serverPlayerRMIClient.send(messageServer);
    }

    @Override
    void sendMessageToGame(int game, Object o) throws IOException {

        MessageServer messageServer = (MessageServer) o;
        List<ServerPlayerRMIHandler> players = playerFromGame.get(game);
        if (players==null)
            return;
        for (ServerPlayerRMIHandler player : players) {
            player.send(messageServer);
        }
    }

    @Override
    void stop() throws IOException {

        for (String s : playerRMI.keySet()) {
            playerRMI.get(s).logout();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers(arg);
    }
}
