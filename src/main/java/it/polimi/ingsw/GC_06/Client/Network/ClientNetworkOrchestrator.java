package it.polimi.ingsw.GC_06.Client.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 12/06/17.
 */
public class ClientNetworkOrchestrator extends Observable implements Observer {

    private Client client;
    @NotNull private final ExecutorService executor;


    public ClientNetworkOrchestrator(@NotNull Client client)
    {
        this.client = client;
        this.executor= Executors.newFixedThreadPool(1);
    }

    public ClientNetworkOrchestrator()
    {
        this.executor= Executors.newFixedThreadPool(1);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void useSocket()
    {
        try {
            client = new ClientSocket(new Socket("127.0.0.1", 1337));
            executor.submit(client);
            client.addObserver(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useRMI()
    {
        client = new ClientRMIHandler();
        client.addObserver(this);
    }

    public void start()
    {
        client.addObserver(this);
        executor.submit(client);
    }

    public synchronized void send(MessageClient messageClient)
    {
        try {
            client.submit(messageClient);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void send(String string)
    {
        try {
            client.submit(string);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        System.out.println("ClientNetworkOrch. Notified from input");
        setChanged();
        notifyObservers(arg);
    }

}
