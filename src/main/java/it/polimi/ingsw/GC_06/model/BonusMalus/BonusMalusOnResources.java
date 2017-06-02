package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by giuseppe on 5/31/17.
 */
public class BonusMalusOnResources implements BonusMalus {
    /** i malus o bonus sulle risorse attaccano una risorsa per volta*/

    private Resource bonusMalusEntity;
    /** questo coefficiente è un termine proporzionale per individuare la quantità di risorse da aggiungere o sottrarre*/
    private int coefficient;

    public BonusMalusOnResources(Resource bonusMalusEntity, int coefficient) {
        this.bonusMalusEntity = bonusMalusEntity;
        this.coefficient = coefficient;
    }

    public void modify(ResourceSet targetResourceSet) {

        if (targetResourceSet.isIncluded(bonusMalusEntity, coefficient)) {
            System.out.println("\nIsIncluded " + targetResourceSet.isIncluded(bonusMalusEntity, coefficient));

            int variation = targetResourceSet.getResources().get(bonusMalusEntity) / coefficient;
            System.out.println("This is variation " + variation);


            /** otteniamo la quantità di cose da sottrarre dal targetResourceSet*/

            targetResourceSet.variateResource(bonusMalusEntity, variation);
        }
    }
}


