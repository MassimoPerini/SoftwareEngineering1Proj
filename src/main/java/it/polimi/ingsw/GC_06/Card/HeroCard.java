package it.polimi.ingsw.GC_06.Card;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

/**
 * Created by massimo on 17/05/17.
 */
public class HeroCard extends Card {
	
	private ArrayList<Effect> effects;
	private ArrayList<Requirement> requirements;
	private ArrayList<Effect> permanentEffects;

    public HeroCard(String name, CardType cardType, ArrayList<Effect> effects, ArrayList<Effect> permanentEffects) {
        super(name, cardType);
        this.effects = effects;
        this.requirements = new ArrayList<>();
        this.permanentEffects = permanentEffects;
        
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

    
    public ArrayList<Effect> getEffects() {
    	return this.effects;
    }
    
    public ArrayList<Effect> getImmediateEffects() {
    	return this.permanentEffects;
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
