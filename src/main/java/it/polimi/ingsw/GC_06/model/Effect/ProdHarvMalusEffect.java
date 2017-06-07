package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 07/06/17.
 */
public interface ProdHarvMalusEffect extends Effect {

    boolean isAllowed(Player player);

}
