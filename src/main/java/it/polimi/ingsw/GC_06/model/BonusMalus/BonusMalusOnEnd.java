package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;


/**
 * Created by giuseppe on 6/6/17.
 */
public class BonusMalusOnEnd {

    private Resource bonusMalusTarget;
    int coefficient;
    private String colour;
    private ActionType actionType;

    public BonusMalusOnEnd(Resource bonusMalusTarget, int coefficient, String colour, ActionType actionType) {
        this.bonusMalusTarget = bonusMalusTarget;
        this.coefficient = coefficient;
        this.colour = colour;
        this.actionType = actionType;
    }

    public void modify(ResourceSet targetResourceSet){

        if(targetResourceSet.isIncluded(bonusMalusTarget)){
            int variation = targetResourceSet.getResources().get(bonusMalusTarget)* coefficient;
            targetResourceSet.variateResource(bonusMalusTarget,variation);
        }

    }
}
