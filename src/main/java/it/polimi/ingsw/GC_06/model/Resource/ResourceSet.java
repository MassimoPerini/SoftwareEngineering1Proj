package it.polimi.ingsw.GC_06.model.Resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by massimo on 11/05/17.
 */
public class ResourceSet {

    private HashMap<Resource, Integer> resources;

    public ResourceSet ()
    {
        super();
        this.resources = new HashMap<>();
    }

    public ResourceSet(ResourceSet resourceSet)
    {
        super();
        this.resources = new HashMap<>(resourceSet.resources);
        //TODO CHECK CLONE!
    }

    private Iterator<Map.Entry<Resource, Integer>> getIterator (ResourceSet resourceSet)
    {
        Set<Map.Entry<Resource, Integer>> newRes = resourceSet.resources.entrySet();
        return newRes.iterator();
    }
    public void variateResource(Resource resource, int amount){

        Integer myQty = this.resources.get(resource);
        if (myQty==null)
            this.resources.put(resource, amount);
        else {
            int res = myQty.intValue() + amount;
            this.resources.replace(resource, res);
        }
    }

    public void variateResource(ResourceSet resourceSet)
    {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(resourceSet);

        while (i.hasNext())
        {
            Map.Entry<Resource, Integer> entry = i.next();
            this.variateResource(entry.getKey(), entry.getValue());
        }

    }
    public boolean isIncluded (ResourceSet resourceSet)
    {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(resourceSet);

        while (i.hasNext())
        {
            Map.Entry<Resource, Integer> entry = i.next();
            if (! isIncluded(entry.getKey(), entry.getValue()))
                return false;
        }
        return true;
    }

    public boolean isIncluded (Resource resource, int amount)
    {
        if (amount>0)
            return true;

        Integer myQty = this.resources.get(resource);
        return myQty!=null && (myQty.intValue()+amount)>=0;
    }

    public boolean isNegativeValuePresent ()
    {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(this);
        while (i.hasNext())
        {
            Map.Entry<Resource, Integer> entry = i.next();
            if (entry.getValue().intValue() < 0)
                return true;
        }
        return false;
    }

    public boolean isPositiveValuePresent ()
    {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(this);
        while (i.hasNext())
        {
            Map.Entry<Resource, Integer> entry = i.next();
            if (entry.getValue().intValue() >= 0)
                return true;
        }
        return false;
    }
}
