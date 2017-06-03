package it.polimi.ingsw.GC_06.model.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by massimo on 03/06/17.
 */
class State extends Observable implements FsmNode {

    private Map<TransitionType, FsmNode> transitionTable;
    private StateName ID;

    public State(StateName ID)
    {
        this.ID = ID;
        transitionTable = new HashMap<>();
    }

    @Override
    public boolean canConsume(final TransitionType transition) {
        return  transitionTable.get(transition) != null;
    }

    @Override
    public FsmNode consume(final TransitionType type) {
        return transitionTable.get(type);

    }

    @Override
    public void addTransition(TransitionType type, FsmNode nextState) {
        transitionTable.put(type, nextState);
    }

    @Override
    public void sendNotify() {
        setChanged();
        notifyObservers(ID);
    }
}
