package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.List;

/**
 * Created by massimo on 12/05/17.
 */

/**
 * @author massimo
 * This class is a DevelopmentCard
 */
public class DevelopmentCard extends Card
{
    private int era;
    private List<Requirement> requirements;
    private List<Effect> immediateEffects;
    private List<ProdHarvEffect> prodHarvEffects;
    private String idColour;

//    private ResourceSet immediateRequirement;

    /**
     *
     * @param name
     * @param era
     * @param requirements
     * @param immediate
     * @param prodHarvEffects
     * @param idColour
     * To create a new DevelopmentCard. No null values are accepted!
     */
    public DevelopmentCard(String name, int era, List<Requirement> requirements, List<Effect> immediate, List<ProdHarvEffect> prodHarvEffects, String idColour)
    {
        super(name);
        if (requirements==null || immediate==null || prodHarvEffects==null || idColour==null)
            throw new NullPointerException();
        this.immediateEffects = immediate;
        this.era = era;
        this.requirements = requirements;
        this.idColour = idColour;
        this.prodHarvEffects = prodHarvEffects;
    }

    /**
     * Check if is satisfied at least one requirement of the card (useful when if you want to take it)
     * @param resourceSet
     * @return
     */
    public boolean isSatisfied(ResourceSet resourceSet)
    {
        for (Requirement requirement:requirements)
        {
            if (requirement.isSatisfied(resourceSet))
                return true;
        }
        return false;
    }

    public List<ProdHarvEffect> getProdHarvEffects() {
        return prodHarvEffects;
    }

    public List<Effect> getImmediateEffects() {
    	return this.immediateEffects;
    }

    public String getIdColour() {
        return idColour;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }
}
