package it.polimi.ingsw.GC_06.playerTools;

import it.polimi.ingsw.GC_06.Resource.ResourceSet;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ResourceEffect {

    private ResourceSet resourceSet;

    public ResourceEffect(ResourceSet resourceSet) {
        this.resourceSet = resourceSet;
    }

    public void execute(Player player){
       ResourceSet resourceSet =  player.getResourceSet();
       resourceSet.variateResource(resourceSet);
    }

}
