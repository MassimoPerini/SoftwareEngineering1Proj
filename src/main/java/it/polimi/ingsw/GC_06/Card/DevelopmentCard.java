package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Requirements;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by massimo on 12/05/17.
 */
public class DevelopmentCard extends Card
{
    private LinkedList<Action> immediateActions;
    private int era;
    private ArrayList<Requirements> requirements;

//    private ResourceSet immediateRequirement;

    public DevelopmentCard(CardType cardType, String name, int era)
    {
        super(name, cardType);
        this.era = era;
        this.requirements = new ArrayList<>();

        immediateActions = new LinkedList<>();
    }

    public void addAction(Action action)
    {
        this.immediateActions.add(action);
    }

    public void addRequirements(Requirements requirements)
    {
        this.requirements.add(requirements);
    }

    //E' una OR
    public boolean isSatisfied(ResourceSet resourceSet)
    {
        for (Requirements requirement:requirements)
        {
            if (requirement.isSatisfied(resourceSet))
                return true;
        }
        return false;

    }

    public void applyImmediateActions ()
    {
        if (! areAllowedImmediateActions())
            throw new IllegalStateException();

        for (Action action:immediateActions)
        {
            action.apply();
        }
        immediateActions=new LinkedList<>();
    }

    public boolean areAllowedImmediateActions()
    {
        for (Action action:immediateActions)
        {
            boolean res = action.isApplicable();
            if (! res)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DevelopmentCard{" +
                "immediateActions=" + immediateActions.toString() +
                ", era=" + era +
                ", requirements=" + requirements.toString() +
                '}';
    }
}
