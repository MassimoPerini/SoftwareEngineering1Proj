package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 06/06/17.
 */
public class DefaultAskUserSelector implements AskUserCard {

    /**
     *
     * @param card la carta sviluppo corrente
     * @param points i punti con cui viene eseguita l'azione di produzione o sviluppo
     * @param player il giocatore che ha eseguito l'azione
     * @return ritorna gli effetti di produzione e raccolto ora associati al giocatore, filtrati con bonusMalus
     */
    @Override
    public List<ProdHarvEffect> askUser(DevelopmentCard card, int points, Player player) {
        List<ProdHarvEffect> effects = card.getProdHarvEffects(points);     //The effect that needs <= points
        List<ProdHarvEffect> userEffects = new LinkedList<>();
        for (ProdHarvEffect effect : effects)
        {
            if (effect.getMalusEffect().size() > 0 && effect.isAllowed(player))     //Allowed malus
            {
                userEffects.add(effect);
            }
        }
        return userEffects;
    }
}
