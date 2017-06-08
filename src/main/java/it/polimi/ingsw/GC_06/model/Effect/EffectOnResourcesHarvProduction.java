package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 01/06/17.
 */
public class EffectOnResourcesHarvProduction{

    private int requiredValue;
    private ResourceSet variateResource;


    public EffectOnResourcesHarvProduction(int requiredValue, ResourceSet variateResource) {
        this.requiredValue = requiredValue;
        this.variateResource = variateResource;
    }

    //TODO effettivamente non esegue se non hai punti sufficienti, ma non dovrebbe per lo meno dare un avviso?
    public void execute(Player player, int points) {
        if (points >= requiredValue)
        {
            player.getResourceSet().variateResource(variateResource);
        }
    }

    public int getValue() {
        return requiredValue;
    }

    public ResourceSet getVariateResource() {
        return variateResource;
    }
}
