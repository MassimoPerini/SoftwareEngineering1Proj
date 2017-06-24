package it.polimi.ingsw.GC_06.model.BonusMalus;

/**
 * Created by giuseppe on 6/12/17.
 */

import it.polimi.ingsw.GC_06.model.Board.ActionPlace;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** bonus e malus che cancella gli effetti da uno spazio azione*/
public class SpecialBonusMalus {

    ActionType actionType;

    public SpecialBonusMalus(ActionType actionType) {
        this.actionType = actionType;
    }

    public void modify(ActionPlace actionPlace){


        for (Effect actionPlaceEffect : actionPlace.getEffects()) {
            actionPlaceEffect = null;
        }
    }

    public boolean isAllowed(ActionType actionType){
        if(this.actionType.equals(actionType)){
            return true;
        }
        else
            return false;
    }

}
