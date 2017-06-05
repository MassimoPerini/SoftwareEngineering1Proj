package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

/**
 * Created by massimo on 05/06/17.
 */

@FunctionalInterface
public interface DevelopmentCardFilter {
    boolean isSatisfiable(DevelopmentCard card);
}
