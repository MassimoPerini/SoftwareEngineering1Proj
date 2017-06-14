package it.polimi.ingsw.GC_06.Client.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import org.jetbrains.annotations.NotNull;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 12/06/17.
 */
public class ClientOrchestrator extends Observable implements Observer {

    @NotNull private final Client client;
    @NotNull private final ExecutorService executor;

    public ClientOrchestrator(@NotNull Client client)
    {
        this.client = client;
        this.executor= Executors.newFixedThreadPool(1);
    }

    public void start()
    {
        client.addObserver(this);
        executor.submit(client);
    }

    public void send(MessageClient messageClient)
    {
        client.submit(messageClient);
    }

    public void send(String string)
    {
        client.submit(string);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Notified from input");
        setChanged();
        notifyObservers(arg);
    }

}
