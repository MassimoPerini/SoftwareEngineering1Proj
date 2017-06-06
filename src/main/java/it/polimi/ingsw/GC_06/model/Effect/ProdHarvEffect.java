package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 01/06/17.
 */
public interface ProdHarvEffect {

    List<Effect> getBonusEffect();
    List<Effect> getMalusEffect();
}
