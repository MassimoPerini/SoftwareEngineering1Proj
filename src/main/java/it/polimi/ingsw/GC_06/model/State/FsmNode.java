package it.polimi.ingsw.GC_06.model.State;

import java.util.Observable;

/**
 * Created by massimo on 03/06/17.
 * The abstract class that represents a node of the fined state machine
 */
public abstract class FsmNode extends Observable {
    public abstract boolean canConsume(TransitionType type);
    public abstract FsmNode consume(TransitionType type);
    public abstract void addTransition (TransitionType type, FsmNode nextState);
    public abstract void sendNotify();
    public abstract StateName getID();
 //   void setPrevNode (FsmNode node);

}
