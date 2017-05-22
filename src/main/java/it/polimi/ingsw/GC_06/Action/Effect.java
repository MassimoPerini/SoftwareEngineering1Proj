package it.polimi.ingsw.GC_06.Action;

/**
 * Created by giuseppe on 5/20/17.
 */
public interface Effect {
    public void execute();
    public boolean isAllowed();
}
