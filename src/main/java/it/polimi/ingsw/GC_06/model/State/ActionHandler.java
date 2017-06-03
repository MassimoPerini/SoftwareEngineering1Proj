package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.Action.Action;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 03/06/17.
 */
public class ActionHandler {

    private List<Action> actions;


    public void addAction(Action action)
    {
        this.actions.add(action);
    }

    public void executeActions()
    {
        for (int i=0;i<actions.size();i++)
        {
            if (actions.get(0).isAllowed())
            {
                actions.get(0).execute();
            }
            actions.remove(0);
        }
    }

    public Action getCurrentAction()
    {
        if (actions.size()>0)
            return actions.get(0);
        else
            throw new IllegalStateException();
    }

    public int sizeAction()
    {
        return actions.size();
    }

}
