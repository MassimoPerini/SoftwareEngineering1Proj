package it.polimi.ingsw.GC_06.Client;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewOrchestrator;

/**
 * Created by massimo on 17/06/17.
 */
public class ClientController {

    private final MainClientModel mainClientModel;
    private ViewOrchestrator viewOrchestrator;

    public ClientController() {
        this.mainClientModel = new MainClientModel();
    }



    public MainClientModel getMainClientModel() {
        return mainClientModel;
    }



    public void setViewOrchestrator(ViewOrchestrator viewOrchestrator) {
        this.viewOrchestrator = viewOrchestrator;
    }

    public ViewOrchestrator getViewOrchestrator() {
        return viewOrchestrator;
    }

    @Override
    public String toString() {
        return "ClientController{" +
                "mainClientModel=" + mainClientModel +
                ", viewOrchestrator=" + viewOrchestrator +
                '}';
    }
}
