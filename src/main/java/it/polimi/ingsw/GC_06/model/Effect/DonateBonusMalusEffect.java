package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 6/20/17.
 */
public class DonateBonusMalusEffect implements Effect{

    private BonusMalusSet bonusMalusSet;

    public DonateBonusMalusEffect(BonusMalusSet bonusMalusSet) {
        this.bonusMalusSet = bonusMalusSet;
    }

    @Override
    public void execute(Player player, Game game) {
        player.getBonusMalusSet().joinSet(bonusMalusSet);
    }
}
