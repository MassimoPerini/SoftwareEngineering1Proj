package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by massimo on 26/06/17.
 */
public interface ServerPlayerRMIClient extends Remote {
    void receive(MessageClient messageClient) throws RemoteException;
}
