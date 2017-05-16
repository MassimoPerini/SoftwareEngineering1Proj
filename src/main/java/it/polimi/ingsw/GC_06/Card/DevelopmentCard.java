package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.LinkedList;

/**
 * Created by massimo on 12/05/17.
 */
public class DevelopmentCard extends Card
{
    private LinkedList<Action> immediateActions;
//    private ResourceSet immediateRequirement;

    public DevelopmentCard(CardType cardType, String name)
    {
        super(name, cardType);
        immediateActions = new LinkedList<>();
    }

    public void addAction(Action action)
    {
        this.immediateActions.add(action);
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

}
