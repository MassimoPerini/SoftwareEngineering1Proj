package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.List;

/**
 * Created by massimo on 12/05/17.
 */
public class DevelopmentCard extends Card
{
    private int era;
    private List<Requirement> requirements;
    private List<Effect> immediateEffects;
    private List<ProdHarvEffect> prodHarvEffects;
    private String idColour;

//    private ResourceSet immediateRequirement;

    public DevelopmentCard(String name, int era, List<Requirement> requirements, List<Effect> immediate, List<ProdHarvEffect> prodHarvEffects, String idColour)
    {
        super(name);
        this.immediateEffects = immediate;
        this.era = era;
        this.requirements = requirements;
        this.idColour = idColour;
        this.prodHarvEffects = prodHarvEffects;
    }

    //E' una OR
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
