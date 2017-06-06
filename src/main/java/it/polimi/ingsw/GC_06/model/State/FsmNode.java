package it.polimi.ingsw.GC_06.model.State;

/**
 * Created by massimo on 03/06/17.
 */
interface FsmNode {
    boolean canConsume(TransitionType type);
    FsmNode consume(TransitionType type);
    void addTransition (TransitionType type, FsmNode nextState);
    void sendNotify();
    void sendNotify(Object o);
 //   void setPrevNode (FsmNode node);

}
