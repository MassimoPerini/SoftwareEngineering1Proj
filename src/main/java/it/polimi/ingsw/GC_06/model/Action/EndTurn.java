package it.polimi.ingsw.GC_06.model.Action;

/**
 * Created by massimo on 01/06/17.
 */
public class EndTurn extends Action {


    public EndTurn(int valueAction) {
        super( valueAction);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
