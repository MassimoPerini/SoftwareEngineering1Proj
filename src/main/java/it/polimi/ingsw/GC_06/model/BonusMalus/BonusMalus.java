package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

/**
 * Created by giuseppe on 5/28/17.
 */
public abstract class BonusMalus  {

    public abstract void modify(ResourceSet resourceSet);
    public abstract void modify();

}
