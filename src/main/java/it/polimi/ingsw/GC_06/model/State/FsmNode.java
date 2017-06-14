package it.polimi.ingsw.GC_06.model.State;

import java.util.Observable;

/**
 * Created by massimo on 03/06/17.
 */
public abstract class FsmNode extends Observable {
    public abstract boolean canConsume(TransitionType type);
    public abstract FsmNode consume(TransitionType type);
    public abstract void addTransition (TransitionType type, FsmNode nextState);
    public abstract void sendNotify();
    public abstract void sendNotify(Object o);
    public abstract StateName getID();
 //   void setPrevNode (FsmNode node);

}
