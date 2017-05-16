package it.polimi.ingsw.GC_06.Resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by massimo on 11/05/17.
 */
public class ResourceSet {

    private HashMap<ResourceType, Integer> resources;

    public ResourceSet ()
    {
        super();
    }

    private Iterator<Map.Entry<ResourceType, Integer>> getIterator (ResourceSet resourceSet)
    {
        Set<Map.Entry<ResourceType, Integer>> newRes = resourceSet.resources.entrySet();
        return newRes.iterator();
    }

    public void addResource(ResourceType resource, int amount)
    {
        if (amount<0)
            throw new IllegalArgumentException();

        Integer myQty = this.resources.get(resource);
        if (myQty==null)
            this.resources.put(resource, amount);
        else {
            int res = myQty.intValue() + amount;
            this.resources.replace(resource, res);
        }
    }

    public void addResource(ResourceSet resourceSet)
    {
        Iterator<Map.Entry<ResourceType, Integer>> i = getIterator(resourceSet);

        while (i.hasNext())
        {
            Map.Entry<ResourceType, Integer> entry = i.next();
            this.addResource(entry.getKey(), entry.getValue());
        }

    }

    public void removeResource (ResourceType resource, int amount)
    {
        if (amount <0)
            throw new IllegalArgumentException();

        if (! isIncluded(resource, amount))
            throw new IllegalArgumentException();

        int myQty = this.resources.get(resource).intValue();
        myQty-=amount;
        resources.replace(resource, myQty);
    }

    public void removeResource (ResourceSet resourceSet) throws IllegalStateException
    {
        if (! isIncluded(resourceSet))
            throw new IllegalStateException();
        Iterator<Map.Entry<ResourceType, Integer>> i = getIterator(resourceSet);

        while (i.hasNext())
        {
            Map.Entry<ResourceType, Integer> entry = i.next();
            this.removeResource(entry.getKey(), entry.getValue());
        }
    }

    public boolean isIncluded (ResourceSet resourceSet)
    {
        Iterator<Map.Entry<ResourceType, Integer>> i = getIterator(resourceSet);

        while (i.hasNext())
        {
            Map.Entry<ResourceType, Integer> entry = i.next();
            if (! isIncluded(entry.getKey(), entry.getValue()))
                return false;
        }
        return true;
    }

    public boolean isIncluded (ResourceType resource, int amount)
    {
        Integer myQty = this.resources.get(resource);
        return myQty!=null && myQty.intValue() >= amount;
    }


}
