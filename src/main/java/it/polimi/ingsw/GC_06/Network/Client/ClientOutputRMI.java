package it.polimi.ingsw.GC_06.Network.Client;

import it.polimi.ingsw.GC_06.Network.Message.MessageClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 11/06/17.
 */
public class ClientOutputRMI implements ClientOutputHandler {


    private ExecutorService pool;

    public ClientOutputRMI() {
        this.pool = Executors.newCachedThreadPool();
    }

    @Override
    public void submit(MessageClient action) {
        pool.submit(() -> {
            //Invocazione metodo remoto
        });
    }
}
