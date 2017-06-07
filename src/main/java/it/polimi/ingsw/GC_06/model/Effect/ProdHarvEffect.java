package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 01/06/17.
 */
public class ProdHarvEffect {

    private List<ProdHarvMalusEffect> malusEffect;
    private List<Effect> bonusEffect;

    public ProdHarvEffect(List<ProdHarvMalusEffect> malusEffect, List<Effect> bonusEffect) {
        this.malusEffect = malusEffect;
        this.bonusEffect = bonusEffect;
    }

    public List<Effect> getBonusEffect()
    {
        return bonusEffect;
    }
    public List<ProdHarvMalusEffect> getMalusEffect()
    {
        return malusEffect;
    }

    public boolean isAllowed(Player player)
    {

        for (ProdHarvMalusEffect prodHarvMalusEffect : malusEffect) {
            if (!prodHarvMalusEffect.isAllowed(player))
                return false;
        }
        return true;
    }

}
