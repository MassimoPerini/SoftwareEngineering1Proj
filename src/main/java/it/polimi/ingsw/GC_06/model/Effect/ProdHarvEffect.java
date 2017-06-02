package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 01/06/17.
 */
public interface ProdHarvEffect {

    void execute(Player player, int points);
    int getValue();

}
