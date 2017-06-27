package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Client.Network.ClientRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by massimo on 26/06/17.
 */
public interface RMIListener extends Remote {
    ServerPlayerRMIClient login(String username, ClientRMI clientRMI) throws RemoteException;
}
