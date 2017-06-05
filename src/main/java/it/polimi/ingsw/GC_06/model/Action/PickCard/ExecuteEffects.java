package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.ActionBoh;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 05/06/17.
 */
public class ExecuteEffects implements Action {

    private List<Effect> effects;
    private Player player;

    public ExecuteEffects(List<Effect> effectList, Player player) {
        super();
        if (effectList==null)
            throw new NullPointerException();
        this.effects = effectList;
        this.player = player;
    }

    @Override
    public void execute() {
        for(Effect effect: effects)
        {
            effect.execute(player);
        }
    }

    @Override
    public boolean isAllowed() {
        return  true;
    }
}
