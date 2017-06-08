package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by giuseppe on 5/31/17.
 */
public class BonusMalusOnResources{
    /** i malus o bonus sulle risorse attaccano una risorsa per volta*/

    private Resource bonusMalusTarget;
    private ResourceSet bonusMalusEntity;
    private ActionType actionType;
    /** questo coefficiente è un termine proporzionale per individuare la quantità di risorse da aggiungere o sottrarre*/
    private double coefficient;

    public BonusMalusOnResources(ResourceSet bonusMalusEntity, double coefficient,  ActionType actionType, Resource bonusMalusTarget) {
        this.bonusMalusEntity = bonusMalusEntity;
        this.coefficient = coefficient;
        this.actionType = actionType;
        this.bonusMalusTarget = bonusMalusTarget;
    }

    public void modify(ResourceSet targetResourceSet) {


        // il target del mio bonus malus coincide con le risorse dell'effeto che stiamo attivando allora lo lanciamo
    //    if (targetResourceSet.getResources().containsKey(bonusMalusTarget)) {
        if (bonusMalusEntity.isIncluded(targetResourceSet)){

            // adesso dobbiamo prendere l'amount del bonusMalusEntity;
            // adesso prendiamo la somma di tutte le quantità di ciascuna risorsa
            int totalAmount = bonusMalusEntity.totalResourceQuantity();

            int variation = (int) (totalAmount*coefficient);
            System.out.println("This is variation " + variation);



            /** otteniamo la quantità di cose da sottrarre dal targetResourceSet*/

            targetResourceSet.variateResource(bonusMalusTarget, variation);

            /** trovato il modo devo solo scriverlo e poi dovrebbe funzionare */
        }
    }

    public boolean isEmpty(){
        return this.bonusMalusEntity.isEmpty();
    }

    public ActionType getActionType() {
        return actionType;
    }

}


