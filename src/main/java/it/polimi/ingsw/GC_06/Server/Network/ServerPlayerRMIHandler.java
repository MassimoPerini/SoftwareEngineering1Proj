package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Client.Network.ClientRMI;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.rmi.server.UnicastRemoteObject.unexportObject;

/**
 * Created by massimo on 26/06/17.
 */
public class ServerPlayerRMIHandler extends Observable implements ServerPlayerRMIClient {

    private final String username;
    private int game;
    private final ClientRMI clientRMI;

    public ServerPlayerRMIHandler(String player, ClientRMI clientRMI) {
        this.username = player;
        this.clientRMI = clientRMI;
        this.game = -1;
    }

    synchronized void send(MessageServer messageServer)
    {
        try {
            System.out.println("RMI SERVER SENDING..."+messageServer.toString());
            clientRMI.receive(messageServer);
        } catch (RemoteException e) {
            LoginHub.getInstance().manageDisconnection(username);
        }
    }

    synchronized void finish()
    {
        try {
            unexportObject(this, true);
        }
        catch (RemoteException e){
            System.out.println("RemoteException");
        }
    }

    @Override
    synchronized public void receive(MessageClient messageClient){
        System.out.println("RMI SERVER RECEIVING..."+messageClient.toString());
        if (game<0)
        {
            return;
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            messageClient.setGame(game);
            messageClient.setPlayer(username);
            setChanged();
            notifyObservers(messageClient);
        });

    }

    String getUsername() {
        return username;
    }

    int getGame() {
        return game;
    }

    synchronized void setGame(int game)
    {
        this.game = game;
    }
}
