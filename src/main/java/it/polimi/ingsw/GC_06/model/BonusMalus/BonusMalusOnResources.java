package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by giuseppe on 5/31/17.
 */
public class BonusMalusOnResources{
    /** i malus o bonus sulle risorse attaccano una risorsa per volta*/


    private Resource bonusMalusResource;
    private int bonusMalusEntity;
    private ActionType actionType;
    /** questo coefficiente è un termine proporzionale per individuare la quantità di risorse da aggiungere o sottrarre*/
    private boolean permanent;

    public BonusMalusOnResources(Resource bonusMalusResource, int bonusMalusEntity, ActionType actionType, boolean permanent) {
        this.bonusMalusResource = bonusMalusResource;
        this.bonusMalusEntity = bonusMalusEntity;
        this.actionType = actionType;
        this.permanent = permanent;
    }

    /** targetResourseSet è quello dell'effetto*/
    public void modify(ResourceSet targetResourceSet) {

            if(targetResourceSet.isIncluded(bonusMalusResource)){
                targetResourceSet.variateResource(bonusMalusResource,bonusMalusEntity);
            }
        }


    public boolean isAllowed(ActionType actionType){
        if(this.actionType.equals(actionType)){
            return true;
        }
        return false;
    }


    public ActionType getActionType() {
        return actionType;
    }

    public boolean isPermanent() {
        return permanent;
    }

}


