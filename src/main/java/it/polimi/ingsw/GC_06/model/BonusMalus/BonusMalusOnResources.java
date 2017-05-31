package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by giuseppe on 5/31/17.
 */
public class BonusMalusOnResources implements BonusMalus {

    private ResourceSet bonusMalusEntity;

    public BonusMalusOnResources(ResourceSet bonusMalusEntity) {
        this.bonusMalusEntity = bonusMalusEntity;
    }

    public void modify(ResourceSet resourceSet){

        if(resourceSet.isIncluded(bonusMalusEntity)) {
            System.out.println("\nIsIncluded "+ resourceSet.isIncluded(bonusMalusEntity));
            resourceSet.variateResource(bonusMalusEntity);
        }
    }
}
