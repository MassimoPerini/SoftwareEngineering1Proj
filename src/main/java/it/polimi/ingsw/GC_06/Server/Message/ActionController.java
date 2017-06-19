package it.polimi.ingsw.GC_06.Server.Message;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by giuseppe on 6/19/17.
 */
public class ActionController implements Observer {

    public ActionController() {
    }

    @Override
    public void update(Observable o, Object arg) {
        MessageClient messageClient = (MessageClient) arg;
        messageClient.execute();
    }
}
