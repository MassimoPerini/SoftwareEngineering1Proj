package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private final List<Requirement> requirements;
    private final List<Effect> immediateEffects;
    private final String idColour;
    private final Map<Integer, List<ProdHarvEffect>> prodHarvEffects;

//    private ResourceSet immediateRequirement;

    /**
     *
     * @param name
     * @param era
     * @param requirements
     * @param immediate
     * @param idColour
     * To create a new DevelopmentCard. No null values are accepted!
     */
    public DevelopmentCard(String name, int era, List<Requirement> requirements, List<Effect> immediate, String idColour, Map<Integer, List<ProdHarvEffect>> prodHarvEffects)
    {
        super(name);
        if (requirements==null || immediate==null || idColour==null)
            throw new NullPointerException();
        this.immediateEffects = immediate;
        this.era = era;
        this.requirements = requirements;
        this.idColour = idColour;
        this.prodHarvEffects = prodHarvEffects;
    }

    public List<ProdHarvEffect> getProdHarvEffects (int value)
    {
        List<ProdHarvEffect> resultEffects = new LinkedList<>();

        for (int i=0; i<=value; i++) {
            resultEffects.addAll(this.prodHarvEffects.get(value));
        }
        return Collections.unmodifiableList(resultEffects);
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

    public List<Effect> getImmediateEffects() {
    	return Collections.unmodifiableList(this.immediateEffects);
    }

    public String getIdColour() {
        return idColour;
    }

    public List<Requirement> getRequirements() {
        return Collections.unmodifiableList(requirements);
    }
}
