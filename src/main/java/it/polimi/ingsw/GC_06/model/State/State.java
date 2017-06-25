package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

import java.util.HashMap;
import java.util.Map;

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
    private MessageServer messageServer;

    public State(StateName ID)
    {
        super();
        this.ID = ID;
        transitionTable = new HashMap<>();
    }

    public State(StateName ID, MessageServer messageServer)
    {
        this(ID);
        this.messageServer = messageServer;
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
        if (messageServer!=null) {
            setChanged();
            notifyObservers(messageServer);
        }
    }


 /*   @Override
    public void setPrevNode(FsmNode node) {
        this.prevNode = node;
    }*/

    public StateName getID() {
        return ID;
    }
}
