package it.polimi.ingsw.GC_06.model.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by massimo on 03/06/17.
 */

/**
 * @author massimo
 * This class is a state of the game.
 * It contains a transitionTable that allows to generate the following state
 * Is an Observable class
 */
class State extends FsmNode {

    private Map<TransitionType, FsmNode> transitionTable;
  //  private FsmNode prevNode;
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
    /*    if (TransitionType.END == type){
            return prevNode;
        }
        transitionTable.get(type).setPrevNode(this);*/
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

    @Override
    public void sendNotify(Object o) {
        setChanged();
        notifyObservers(o);
    }

 /*   @Override
    public void setPrevNode(FsmNode node) {
        this.prevNode = node;
    }*/

    public StateName getID() {
        return ID;
    }
}
