package it.polimi.ingsw.GC_06.Effect;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.playerTools.Player;

/**
 * Created by giuseppe on 5/22/17.
 */
public class EffectOnAction implements Effect {

    private Action action;
    private Player player;


    public EffectOnAction(int points, Action action) {
        super();
        this.action = action;
    }

    @Override
    public void execute(Player player) {
    //    action.setPlayer(player);     //TODO chiedere a Martin
        action.execute();
    }
}
