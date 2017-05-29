package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public class EffectOnResources implements Effect {

    private ResourceSet resourceSet;
    
    public EffectOnResources(ResourceSet resourceSet) {
    	super();
        this.resourceSet = resourceSet;
    }

    public void execute(Player player){
       player.variateResource(this.resourceSet);
    }


    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(ResourceSet resourceSet) {
        this.resourceSet = resourceSet;
    }
}
