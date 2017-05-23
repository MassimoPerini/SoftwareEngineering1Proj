package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Component;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public interface Action {

    public void  execute();
    public boolean isAllowed();
}
