package it.polimi.ingsw.GC_06.model.Action.Actions;

/**
 * Created by massimo on 21/06/17.
 * l'interfaccia impolementata per le azioni di inibizione del giocatore
 */
public interface Blocking {
    void setOptionalParams(Object object);
    void userLoggedOut(String user);
}
