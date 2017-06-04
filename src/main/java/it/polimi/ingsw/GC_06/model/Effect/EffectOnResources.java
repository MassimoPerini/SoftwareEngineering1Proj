package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.PlayType;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public class EffectOnResources implements Effect {

    private ResourceSet resourceSet;
    private PlayType playType;
    
    public EffectOnResources(ResourceSet resourceSet,PlayType playType) {
    	super();
        this.resourceSet = resourceSet;
        this.playType = playType;
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
