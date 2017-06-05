package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;

import static it.polimi.ingsw.GC_06.model.Action.PlayType.endTurn;

/**
 * Created by massimo on 01/06/17.
 */
public class EndTurn extends Action {


    public EndTurn(int valueAction, BonusMalusHandler bonusMalusHandler) {
        super(endTurn, valueAction,bonusMalusHandler);
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
