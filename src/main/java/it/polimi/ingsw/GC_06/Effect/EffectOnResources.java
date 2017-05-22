package it.polimi.ingsw.GC_06.Effect;

import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public class EffectOnResources implements Effect {

    private ResourceSet resourceSet;

    public EffectOnResources(ResourceSet resourceSet) {
        this.resourceSet = resourceSet;
    }

    public void execute(Player player){
       ResourceSet resourceSet =  player.getResourceSet();
       resourceSet.variateResource(resourceSet);
    }


    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(ResourceSet resourceSet) {
        this.resourceSet = resourceSet;
    }
}
