package it.polimi.ingsw.GC_06.Client.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 12/06/17.
 * this class handles the comunication client side, and is both an observer and an observable class
 */
public class ClientNetworkOrchestrator extends Observable implements Observer {

    private Client client;
    private final ExecutorService executor;
    private boolean actionFinished;
    private ExecutorService executorService;

    public ClientNetworkOrchestrator(Client client)
    {
        this.client = client;
        this.executor= Executors.newFixedThreadPool(1);

    }

    public ClientNetworkOrchestrator()
    {
        this.executor= Executors.newFixedThreadPool(1);
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * adds a client connected via socket
     */
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

    /**
     * adds a client connected via RMI
     */
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

    /**
     * sends a message to the client
     * @param messageClient message to be sent
     */
    public synchronized void send(MessageClient messageClient)
    {
        try {
            client.submit(messageClient);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends a string to the client
     * @param string string to be sent
     */
    public void send(String string)
    {
        try {
            client.submit(string);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean isActionFinished() {
        return actionFinished;
    }

    public synchronized void setActionFinished(boolean actionFinished) {
        this.actionFinished = actionFinished;
    }

    @Override
    public void update(Observable o, Object arg) {
        executorService.submit(() -> {
            setChanged();
            notifyObservers(arg);
        });
    }

}
