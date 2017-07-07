package it.polimi.ingsw.GC_06.Client.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Network.RMIListener;
import it.polimi.ingsw.GC_06.Server.Network.ServerPlayerRMIClient;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by massimo on 12/06/17.
 */
public class ClientRMIHandler extends Client implements ClientRMI {

    private RMIListener rmiListener;
    private ServerPlayerRMIClient serverPlayerRMIClient;

    public ClientRMIHandler()
    {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 5299);
            System.out.print("RMI registry bindings: ");
            String remoteObjectName = "lorenzo_magnifico";
            this.rmiListener = (RMIListener) registry.lookup(remoteObjectName);
            UnicastRemoteObject.exportObject(this, 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    synchronized public void submit(MessageClient action) throws RemoteException {
        try {
            System.out.println("CLIENT RMI SENDING: "+action.toString());
            serverPlayerRMIClient.receive(action);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    synchronized public void submit(String string) throws RemoteException {
        //Sono all'inizio
        try {
         //   rmiListener.login("max", null);
            System.out.println("CLIENT RMI SENDING: "+string);
            ServerPlayerRMIClient serverPlayerRMIClient = rmiListener.login(string, this);
            if (serverPlayerRMIClient == null) {
                return;
            } else {
                this.serverPlayerRMIClient = serverPlayerRMIClient;
            }
        }
        catch (RemoteException e)
        {e.printStackTrace();}
    }

    @Override
    synchronized public void run() {
        //Ascolto e ricevo azioni
    }

    @Override
    synchronized public void receive(MessageServer messageServer) throws RemoteException{
        System.out.println("RMI CLIENT IN RICEZIONE "+messageServer.toString());

        setChanged();
        notifyObservers(messageServer);
    }
}
