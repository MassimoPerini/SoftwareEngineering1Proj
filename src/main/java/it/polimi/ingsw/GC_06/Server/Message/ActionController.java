package it.polimi.ingsw.GC_06.Server.Message;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by giuseppe on 6/19/17.
 */
public class ActionController implements Observer {

    private ExecutorService executorService;

    public ActionController() {
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void update(Observable o, Object arg) {
        MessageClient messageClient = (MessageClient) arg;
        Future future = executorService.submit(messageClient);
    }
}
