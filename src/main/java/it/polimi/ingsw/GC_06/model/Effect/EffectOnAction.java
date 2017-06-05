package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.ActionBoh;
import it.polimi.ingsw.GC_06.model.Action.PlayType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/22/17.
 */
public class EffectOnAction implements Effect {

    private PlayType playType;
    private ArrayList<ActionBoh> actions;
    private Player player;


    public EffectOnAction(ArrayList<ActionBoh> actions, PlayType playType) {
        super();
        this.playType = playType;
        this.actions = actions;
    }

    @Override
    public void execute(Player player) {
        for (ActionBoh a : actions) a.setPlayer(player);
        // TODO decidere se singola o arrayList
        for (ActionBoh a : actions) a.execute();

       //TODO da rivedere per la PickCard

    }
}
