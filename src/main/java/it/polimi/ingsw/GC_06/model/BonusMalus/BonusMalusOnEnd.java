package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by giuseppe on 6/22/17.
 */

/** queste sono le ultime 4 carte della colonna III delle scomuniche */

public class BonusMalusOnEnd {

    private Resource resourceTarget;
    /**
     * questa è la risorsa da diminuire
     */
    private List<Resource> activeResourceList;
    private ActionType actionType;
    private double coefficient;
    private boolean permanent;

    public BonusMalusOnEnd(Resource resourceTarget, List<Resource> activeResourceList, ActionType actionType,double coefficient,boolean permanent) {
        this.resourceTarget = resourceTarget;
        this.activeResourceList= activeResourceList;
        this.actionType = actionType;
        this.coefficient = coefficient;
        this.permanent = permanent;
    }

    /**
     * il resourceSet è quello in base a cui cambiare
     */
    public void modify(Player player, ResourceSet activeResourceSet) {

        int totalAmount = 0;
        for (Resource resource : activeResourceList) {
            int tmp = activeResourceSet.getResourceAmount(resource);
            totalAmount = tmp + totalAmount;
        }

        totalAmount = (int)(totalAmount*coefficient);
        ResourceSet variationSet = new ResourceSet();

        /* non mi permette di dare un valore minore di zero */
        if(player.getResourceSet().getResourceAmount(resourceTarget) + totalAmount < 0){
            totalAmount = player.getResourceSet().getResourceAmount(resourceTarget)*(-1);
        }

        variationSet.variateResource(resourceTarget,totalAmount);

        player.variateResource(variationSet);

    }

    public boolean isAllowed(ResourceSet resourceSet) {

        for (Resource resource : activeResourceList) {
            if (!resourceSet.getResources().keySet().contains(resource)) {
                return false;
            }
        }
        return true;
    }


    public boolean check(ActionType actionType,ResourceSet resourceSet){
        if(this.actionType.equals(actionType) && isAllowed(resourceSet)){
            return true;
        }
        else
            return false;
    }

    public boolean isPermanent() {
        return permanent;
    }
}


