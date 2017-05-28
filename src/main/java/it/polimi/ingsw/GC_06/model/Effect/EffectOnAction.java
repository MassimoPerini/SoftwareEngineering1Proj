package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.BoardAction;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/22/17.
 */
public class EffectOnAction implements Effect {

    private BoardAction action;
    private Player player;


    public EffectOnAction(int points, BoardAction action) {
        super();
        this.action = action;
    }

    @Override
    public void execute(Player player) {
    //    action.setPlayer(player);     //TODO chiedere a Martin
        action.execute();
    }
}
