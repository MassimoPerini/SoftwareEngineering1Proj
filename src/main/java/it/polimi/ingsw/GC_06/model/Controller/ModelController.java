package it.polimi.ingsw.GC_06.model.Controller;

/**
 * Created by gabri on 11/06/2017.
 */
public interface ModelController {
    void ActOnModel (Object objectFromSocket, String userID);
    /** l'interfaccia sarà poi implementata da una classe per ogni possibile azione dell'utente */
}
