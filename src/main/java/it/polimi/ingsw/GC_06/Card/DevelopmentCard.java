package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Action.Effect;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by massimo on 12/05/17.
 */
public class DevelopmentCard extends Card
{
    private ArrayList<Effect> effects;
    private int era;
    private ArrayList<Requirement> requirements;

//    private ResourceSet immediateRequirement;

    public DevelopmentCard(CardType cardType, String name, int era)
    {
        super(name, cardType);
        this.era = era;
        this.requirements = new ArrayList<>();
        this.effects = new ArrayList<>();
    }

    public void addEffect(Effect effect)
    {
        this.effects.add(effect);
    }

    public void addRequirements(Requirement requirement)
    {
        this.requirements.add(requirement);
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

    public void applyEffects ()
    {
        if (! areAllowedEffects())
            throw new IllegalStateException();

        for (Effect effect :effects)
        {
            effect.execute();
        }
        effects = new ArrayList<>();
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

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }
}
