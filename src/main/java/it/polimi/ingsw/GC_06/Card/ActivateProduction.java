package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Player;

import java.util.Observable;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ActivateProduction extends Observable implements Action {
    private Player player;


    public ActivateProduction(int points, Player player) {
        this.points = points;
        this.player = player;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
