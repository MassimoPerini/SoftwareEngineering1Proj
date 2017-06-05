package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;

import java.util.List;

/**
 * Created by massimo on 06/06/17.
 */
public class DefaultAskUserSelector implements AskUserCard {

    @Override
    public boolean askUser(DevelopmentCard card, int points) {
        List<ProdHarvEffect> effects = card.getProdHarvEffects(points);
        for (ProdHarvEffect effect : effects)
        {
            if (effect.getMalusEffect().size() > 0)
            {
                return true;
            }
        }
        return false;
    }
}
