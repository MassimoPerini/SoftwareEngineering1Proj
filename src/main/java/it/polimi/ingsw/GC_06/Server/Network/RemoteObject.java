package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Client.Network.ClientRMI;
import it.polimi.ingsw.GC_06.Client.Network.ClientRmiRemote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by gabri on 19/06/2017.
 */
public interface RemoteObject extends Remote {
    void registerClient(ClientRmiRemote clientRMI) throws RemoteException;
    void executeActionOnTower() throws RemoteException;
    void executePayCard() throws RemoteException;
    void executePickCard() throws RemoteException;
    void executeActionOnProdHarv() throws RemoteException;
    void executeChooseProdHarvCards() throws RemoteException;
    void executeActionOnMarketCounsil() throws RemoteException;
    void executeChooseParchment() throws RemoteException;
    void executePayVatica() throws RemoteException;
}
