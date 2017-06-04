package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.PlayType;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 01/06/17.
 */
public class EffectOnResourcesHarvProduction implements ProdHarvEffect{

    private int requiredValue;
    private ResourceSet variateResource;
    private PlayType playType;

    public EffectOnResourcesHarvProduction(int requiredValue, ResourceSet variateResource,PlayType playType) {
        this.requiredValue = requiredValue;
        this.variateResource = variateResource;
        this.playType = playType;
    }


    @Override
    public void execute(Player player, int points) {
        if (points >= requiredValue)
        {
            player.getResourceSet().variateResource(variateResource);
        }
    }

    @Override
    public int getValue() {
        return requiredValue;
    }

    public ResourceSet getVariateResource() {
        return variateResource;
    }
}
