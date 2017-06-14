package it.polimi.ingsw.GC_06.Client.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

import java.util.Observable;

/**
 * Created by massimo on 14/06/17.
 */
public abstract class Client extends Observable implements Runnable {
    public abstract void submit(MessageClient action);
    public abstract void submit(String string);

}
