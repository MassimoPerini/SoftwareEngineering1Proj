package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

/**
 * Created by massimo on 06/06/17.
 */
public class DefaultProductionCardSelector implements ProdHarvFilterCard {

    @Override
    public boolean isSatisfiable(DevelopmentCard card) {
        return card.getIdColour().equals("YELLOW");
    }
}
