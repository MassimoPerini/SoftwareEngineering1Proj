package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Component;

/**
 * Created by giuseppe on 5/20/17.
 */
public interface Action {

    public void  execute();
    public boolean isAllowed();
}
