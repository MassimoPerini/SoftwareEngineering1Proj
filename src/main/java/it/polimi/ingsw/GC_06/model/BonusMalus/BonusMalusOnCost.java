package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by giuseppe on 6/6/17.
 */
public class BonusMalusOnCost {

    /** la logica di controllo la delego di nuovo all'handeler*/

    /** sarà lo sconto o l'aumento del prezzo*/

    private ResourceSet bonusMalusEntity;
    private String colourTarget;
    private ActionType actionType;

    public BonusMalusOnCost(ResourceSet bonusMalusEntity, String colourTarget, ActionType actionType) {
        this.bonusMalusEntity = bonusMalusEntity;
        this.colourTarget = colourTarget;
        this.actionType = actionType;
    }

    /** chiediamo a massi questa cosa perchè non ricordo la struttura delle carte */
    public void modify(DevelopmentCard developmentCard){

        //controlliamo se il bonus malusEntity isIncluded nel costo della carta

        // qui semplicemente avviene la modifica del costo di una carta
        //developmentCard.getRequirements(). arriveremo ad un .variateResource(bonusMalusEntity)
    }
}
