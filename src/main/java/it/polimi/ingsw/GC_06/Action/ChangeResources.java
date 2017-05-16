package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Player;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

/**
 * Created by massimo on 12/05/17.
 */
public class ChangeResources implements Action {

    private ResourceSet resourceToAdd;
    private ResourceSet resourceToRemove;

    private ResourceSet resourceTarget;

    public ChangeResources(ResourceSet resourceToAdd, ResourceSet resourceToRemove, Player player)
    {
        this.resourceToAdd = resourceToRemove;
        this.resourceToRemove = resourceToRemove;
        this.resourceTarget = player.getResources();
    }

    public boolean isApplicable()
    {
        if (resourceToRemove==null)
            return true;
        return resourceTarget.isIncluded(resourceToRemove);
    }

    public void apply()
    {
        if (! isApplicable())
            throw new IllegalStateException();
        if (resourceToAdd!=null) {
            resourceTarget.addResource(resourceToAdd);
        }
        if (resourceToRemove != null) {
            resourceTarget.removeResource(resourceToRemove);
        }
    }

}
