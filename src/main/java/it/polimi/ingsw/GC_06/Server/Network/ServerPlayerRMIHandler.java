package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Client.Network.ClientRMI;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.rmi.NoSuchObjectException;
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
    /*    try {
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }

    synchronized public void send(MessageServer messageServer)
    {
        try {
            System.out.println("RMI SERVER SENDING..."+messageServer.toString());
            clientRMI.receive(messageServer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    synchronized public void logout() throws RemoteException
    {
        unexportObject(this, true);
    }

    synchronized public void unreferenced() throws NoSuchObjectException {
        unexportObject(this, true); // needs to be in a try/catch block of course
    }

    @Override
    synchronized public void receive(MessageClient messageClient)throws RemoteException {
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

    synchronized public void setGame(int game)
    {
        this.game = game;
    }
}
