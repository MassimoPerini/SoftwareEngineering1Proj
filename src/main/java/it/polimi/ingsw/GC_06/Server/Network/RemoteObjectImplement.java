package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Client.Network.ClientRMI;
import it.polimi.ingsw.GC_06.Client.Network.ClientRmiRemote;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gabri on 19/06/2017.
 */
public class RemoteObjectImplement implements RemoteObject {
    private Set<ClientRmiRemote> clients;



    public RemoteObjectImplement() {
        this.clients = new HashSet<>();
    }

    @Override
    public void registerClient(ClientRmiRemote clientRMI) throws RemoteException {
        this.clients.add(clientRMI);
    }

    @Override
    public void executeActionOnTower() throws RemoteException {
    }

    @Override
    public void executePayCard() throws RemoteException {

    }

    @Override
    public void executePickCard() throws RemoteException {

    }

    @Override
    public void executeActionOnProdHarv() throws RemoteException {

    }

    @Override
    public void executeChooseProdHarvCards() throws RemoteException {

    }

    @Override
    public void executeActionOnMarketCounsil() throws RemoteException {

    }

    @Override
    public void executeChooseParchment() throws RemoteException {

    }

    @Override
    public void executePayVatica() throws RemoteException {

    }
}
