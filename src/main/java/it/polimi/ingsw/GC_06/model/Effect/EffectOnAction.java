package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/22/17.
 * @deprecated
 */
public class EffectOnAction implements Effect {


    private TransitionType action;
    private Object object;


    public EffectOnAction(TransitionType action, Object object) {
        super();
        this.action = action;
        this.object = object;
    }

    @Override
    public void execute(Player player,Game game) {

    //    game.getGameStatus().changeState(action, object);
    }
}