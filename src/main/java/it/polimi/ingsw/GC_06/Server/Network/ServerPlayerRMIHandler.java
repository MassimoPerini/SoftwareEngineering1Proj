package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Client.Network.ClientRMI;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.util.Observable;

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

    public void send(MessageServer messageServer)
    {
        try {
            clientRMI.receive(messageServer);
            System.out.println("RMI SERVER SENDING...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void logout() throws RemoteException
    {
        unexportObject(this, true);
    }

    public void unreferenced() throws NoSuchObjectException {
        unexportObject(this, true); // needs to be in a try/catch block of course
    }

    @Override
    public void receive(MessageClient messageClient)throws RemoteException {
        System.out.println("RMI SERVER RECEIVING..."+messageClient.toString());
        if (game<0)
        {
            return;
        }
        messageClient.setGame(game);
        messageClient.setPlayer(username);
        setChanged();
        notifyObservers(messageClient);
    }

    public void setGame(int game)
    {
        this.game = game;
    }
}
