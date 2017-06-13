package it.polimi.ingsw.GC_06.model.Effect;


import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public interface Effect {
    void execute(Player player,Game game);
}
