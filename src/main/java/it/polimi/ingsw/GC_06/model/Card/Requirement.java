package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.Serializable;

/**
 * Created by massimo on 18/05/17.
 */

/**
 * This class is a Requirement of a card (usually DevelopmentCard)
 * Created by massimo on 18/05/17.
 * @author massimo
 */
public class Requirement implements Serializable {

    //TODO TEST!!!
    private final ResourceSet requirements;
    private final ResourceSet cost;

    /**
     * Requirements and costs are handled differently
     * @param requirements
     * @param cost
     */
    public Requirement(ResourceSet requirements, ResourceSet cost) {
        if (requirements == null)
            requirements = new ResourceSet();
        if (cost == null)
            cost = new ResourceSet();

        this.requirements = requirements;
        this.cost = cost;
    }

    public Requirement(Requirement requirement)
    {
        this.requirements = new ResourceSet(requirement.requirements);
        this.cost = new ResourceSet(requirement.cost);
    }

    /**
     * Is this requirement satisfied?
     * @param resourceSet
     * @return
     */
    public boolean isSatisfied (ResourceSet resourceSet)
    {
        return resourceSet.isIncluded(requirements) && resourceSet.isIncluded(cost);
    }

    /**
     * It applies the costs. //TODO change the name
     * @param player
     * @deprecated //TODO USE Player.variateResource(Requirement)
     */
    public void doIt (Player player)
    {
        player.variateResource(cost);
    }

    public ResourceSet getCost() {
        return cost;
    }

    public ResourceSet getRequirements() {
        return requirements;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "requirements=" + requirements.toString() +
                ", cost=" + cost.toString() +
                '}';
    }
}
