package it.polimi.ingsw.GC_06.model.Cancel;

import it.polimi.ingsw.GC_06.model.Action.Action;

/**
 * Created by massimo on 26/05/17.
 */
/**@deprecated */
public class TestAdapter implements NetworkAdapter {
    @Override
    public void send(Action action) {
        action.execute();
    }
}
