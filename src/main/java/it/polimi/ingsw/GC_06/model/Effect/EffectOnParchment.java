package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.PlayType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnParchment implements Effect {


    private PlayType playType;

    public EffectOnParchment(PlayType playType) {
        this.playType = playType;
    }

    @Override
    public void execute(Player player) {
       // TODO quando sarà fatta la view notify(player.view())
        // TODO notifica al controller di cambiare lo stato del gioco in PrivilegeDecisionStatus

    }
}
