package it.polimi.ingsw.GC_06.Card;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.Action.Effect;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

/**
 * Created by massimo on 17/05/17.
 */
public class HeroCard extends Card {
	
	private ArrayList<Effect> effects;
	private ArrayList<Requirement> requirements;

    public HeroCard(String name, CardType cardType) {
        super(name, cardType);
        this.effects = new ArrayList<>();
        this.requirements = new ArrayList<>();
    }
    
    public void addEffect(Effect effect)
    {
        this.effects.add(effect);
    }

    public void addRequirements(Requirement requirement)
    {
        this.requirements.add(requirement);
    }
    
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
    //TODO da fare il toString


}
