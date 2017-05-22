package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.ArrayList;

/**
 * Created by massimo on 12/05/17.
 */
public class DevelopmentCard extends Card
{
    private ArrayList<Effect> effects;
    private int era;
    private ArrayList<Requirement> requirements;
    private ArrayList<Effect> immediateEffects;
    private String idColour;

//    private ResourceSet immediateRequirement;

    public DevelopmentCard(CardType cardType, String name, int era, ArrayList<Effect> effects, ArrayList<Requirement> requirements, String idColour)
    {
        super(name, cardType);
        this.era = era;
        this.requirements = new ArrayList<>();
        this.effects = effects;
        this.idColour = idColour;
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

    public ArrayList<Effect> getEffects() {
    	return this.effects;
    }
    
    public ArrayList<Effect> getImmediateEffects() {
    	return this.immediateEffects;
    }

    public boolean areAllowedEffects()
    {
        for (Effect effect:effects)
        {
            boolean res = effect.isAllowed();
            if (! res)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DevelopmentCard{" +
                "effects=" + effects.toString() +
                ", era=" + era +
                ", requirements=" + requirements.toString() +
                '}';
    }

    public String getIdColour() {
        return idColour;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }
}
