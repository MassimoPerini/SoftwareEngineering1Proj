package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.List;


/**
 * Created by giuseppe on 6/6/17.
 */

/** questo diventerà un malus che cambia i valori interi in situazioni come la fine il costo di powerup */
public class BonusMalusOnSettings {

    private int bonusMalusEntity;
    private int coefficient;
    private List<String> colours;
    private ActionType actionType;
    private boolean permanent;
    private boolean ON;

    public BonusMalusOnSettings(int bonusMalusEntity, int coefficient, List<String> colours, ActionType actionType) {
        this.coefficient = coefficient;
        this.colours = colours;
        this.actionType = actionType;
        this.ON = true;
    }

    public void modify(int endPoints){

        endPoints = this.bonusMalusEntity;

    }

    public boolean isAllowed(String colour,ActionType actionType){
        if(colours.contains(colour) && this.actionType.equals(actionType)){
            return true;
        }
        return false;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public List<String> getColour() {
        return colours;
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
