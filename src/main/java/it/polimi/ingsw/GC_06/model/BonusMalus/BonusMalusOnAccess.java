package it.polimi.ingsw.GC_06.model.BonusMalus;

/**
 * Created by giuseppe on 6/12/17.
 */
public class BonusMalusOnAccess {

    private ActionType actionType;
    private boolean permanent;

    public void  modify(boolean result){
        result = !result;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public boolean isPermanent() {
        return permanent;
    }
}
