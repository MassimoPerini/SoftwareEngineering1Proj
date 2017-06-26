package it.polimi.ingsw.GC_06.model.Action.Actions;

/**
 * Created by massimo on 05/06/17.
 */
public interface Action{

    void execute() throws InterruptedException;
    boolean isAllowed() throws InterruptedException;
}
