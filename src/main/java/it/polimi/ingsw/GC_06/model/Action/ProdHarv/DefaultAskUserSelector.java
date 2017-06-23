package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 06/06/17.
 */
public class DefaultAskUserSelector implements AskUserCard {

    @Override
    public List<ProdHarvEffect> askUser(DevelopmentCard card, int points, Player player) {
        List<ProdHarvEffect> effects = card.getProdHarvEffects(points);
        for (ProdHarvEffect effect : effects)
        {
            if (effect.getMalusEffect().size() > 0 && effect.isAllowed(player))
            {
                effects.add(effect);
            }
        }
        return effects;
    }
}
