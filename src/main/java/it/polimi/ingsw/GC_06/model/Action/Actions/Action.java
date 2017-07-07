package it.polimi.ingsw.GC_06.model.Action.Actions;

/**
 * Created by massimo on 05/06/17.
 * L'interfaccia implmementata dalle azioni di gioco
 */
public interface Action{

    void execute() throws InterruptedException;
    boolean isAllowed() throws InterruptedException;
}
