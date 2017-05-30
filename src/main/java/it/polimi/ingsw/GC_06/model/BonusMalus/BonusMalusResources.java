package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by giuseppe on 5/28/17.
 */
public class BonusMalusResources extends BonusMalus {

    private ResourceSet bonusMalusEntity;


    public BonusMalusResources(ResourceSet bonusMalusEntity) {
        this.bonusMalusEntity = bonusMalusEntity;
    }

    @Override
    public void modify(ResourceSet resourceSet) {

        for(Resource resource :resourceSet.getResources().keySet()){
            if(resource.equals(resourceSet.getResources().keySet())){
                resourceSet.getResources().put(resource,resourceSet.getResources().get(resource) +
                        bonusMalusEntity.getResources().get(resource));
            }
        }

    }

    /*@Override
    public void filter() {

    }*/


    @Override
    public void modify() {

    }
}

