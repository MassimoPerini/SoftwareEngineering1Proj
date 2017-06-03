package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/22/17.
 */
public class EffectOnAction implements Effect {

    private ArrayList<Action> actions;
    private Player player;


    public EffectOnAction(int points, ArrayList<Action> actions) {
        super();
        this.actions = actions;
    }

    @Override
    public void execute(Player player) {
        for (Action a : actions) a.setPlayer(player);
        // TODO decidere se singola o arrayList
        for (Action a : actions) a.execute();
    }
}
