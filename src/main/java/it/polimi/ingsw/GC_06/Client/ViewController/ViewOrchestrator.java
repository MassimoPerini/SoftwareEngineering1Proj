package it.polimi.ingsw.GC_06.Client.ViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;

import java.util.Observer;

/**
 * Created by massimo on 16/06/17.
 */
public interface ViewOrchestrator extends Observer {

    void change(ClientStateName state, String property);
    void execute(String [] args);



}
