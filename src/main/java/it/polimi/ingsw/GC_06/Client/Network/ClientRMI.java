package it.polimi.ingsw.GC_06.Client.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by massimo on 26/06/17.
 */
public interface ClientRMI extends Remote {
    void receive(MessageServer messageServer) throws RemoteException;
}
