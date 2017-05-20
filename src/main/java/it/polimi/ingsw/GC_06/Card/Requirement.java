package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.ArrayList;

/**
 * Created by massimo on 18/05/17.
 */
public class Requirement {

    //TODO TEST!!!
    private ResourceSet requirements;
    private ResourceSet cost;

    public Requirement(ResourceSet requirements, ResourceSet cost) {
        if (requirements == null)
            requirements = new ResourceSet();
        if (cost == null)
            cost = new ResourceSet();

        this.requirements = requirements;
        this.cost = cost;
    }

    public boolean isSatisfied (ResourceSet resourceSet)
    {
        return resourceSet.isIncluded(requirements) && resourceSet.isIncluded(cost);
    }

    public void doIt (ResourceSet resourceSet)
    {
        if (!isSatisfied(resourceSet))
            throw new IllegalArgumentException();
        resourceSet.removeResource(cost);
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "requirements=" + requirements.toString() +
                ", cost=" + cost.toString() +
                '}';
    }
}
