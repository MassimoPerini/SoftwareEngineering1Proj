package it.polimi.ingsw.GC_06.Network.Client;

import it.polimi.ingsw.GC_06.Network.Message.MessageClient;

import java.util.Observable;

/**
 * Created by massimo on 14/06/17.
 */
public abstract class Client extends Observable implements Runnable {
    public abstract void submit(MessageClient action);
    public abstract void submit(String string);

}
