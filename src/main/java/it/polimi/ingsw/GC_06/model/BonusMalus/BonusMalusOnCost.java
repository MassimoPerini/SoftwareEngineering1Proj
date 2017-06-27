package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Card.Card;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.List;

/**
 * Created by giuseppe on 6/6/17.
 */
public class BonusMalusOnCost {

    /** la logica di controllo la delego di nuovo all'handeler*/

    /** sarà lo sconto o l'aumento del prezzo*/

    private ResourceSet bonusMalusEntity;
    private List<String> colourTarget;
    private ActionType actionType;
    private boolean permanent;
    private boolean ON;

    public BonusMalusOnCost(ResourceSet bonusMalusEntity, List<String> colourTarget, ActionType actionType,boolean permanent) {
        this.bonusMalusEntity = bonusMalusEntity;
        this.colourTarget = colourTarget;
        this.actionType = actionType;
        this.permanent = permanent;
    }

    /** chiediamo a massi questa cosa perchè non ricordo la struttura delle carte */
    public DevelopmentCard modify(DevelopmentCard developmentCard){

        /** voglio modifciare solo fakeCard non il resto */
        if(isAllowed(developmentCard)){
            for(Requirement requirement : developmentCard.getRequirements()){
               requirement.getCost().variateResource(bonusMalusEntity);
            }


        }

        return developmentCard;
    }

    public boolean isAllowed(DevelopmentCard developmentCard){

        for(String colour : colourTarget) {
            if (colour.equals(developmentCard.getIdColour())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAllowed(ActionType actionType){
        if(this.actionType.equals(actionType)){
            return true;
        }
        else{
            return false;
        }
    }
    public ActionType getActionType() {
        return actionType;
    }


    public void setON(boolean ON) {
        this.ON = ON;
    }

    public boolean isON() {
        return ON;
    }

    public boolean isPermanent() {
        return permanent;
    }
}
