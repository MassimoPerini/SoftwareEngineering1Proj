package it.polimi.ingsw.GC_06.model.Network;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.ActionBoh;

/**
 * Created by massimo on 26/05/17.
 */
public class TestAdapter implements NetworkAdapter {
    @Override
    public void send(Action action) {
        action.execute();
    }
}
