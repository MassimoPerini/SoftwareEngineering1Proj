package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;


/**
 * Created by giuseppe on 6/6/17.
 */
public class BonusMalusOnEnd {

    private Resource bonusMalusTarget;
    private String colour;
    private ActionType actionType;
    private boolean permanent;
    private boolean ON;

    public BonusMalusOnEnd(Resource bonusMalusTarget,String colour, ActionType actionType) {
        this.bonusMalusTarget = bonusMalusTarget;

        this.colour = colour;
        this.actionType = actionType;
    }

    public void modify(ResourceSet targetResourceSet){

        if(targetResourceSet.isIncluded(bonusMalusTarget)){
            int variation = targetResourceSet.getResources().get(bonusMalusTarget);
            targetResourceSet.variateResource(bonusMalusTarget,variation);
        }

    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getColour() {
        return colour;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public boolean isON() {
        return ON;
    }

    public void setON(boolean ON) {
        this.ON = ON;
    }
}
