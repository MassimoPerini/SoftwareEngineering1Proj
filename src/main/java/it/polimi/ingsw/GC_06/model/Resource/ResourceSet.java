package it.polimi.ingsw.GC_06.model.Resource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by massimo on 11/05/17.
 * @author massimo
 * This class is a Set of Resources.
 */
public class ResourceSet {

    private HashMap<Resource, Integer> resources;

    public ResourceSet() {
        super();
        this.resources = new HashMap<>();
    }

    /**
     *
     * @param resourceSet
     */
    public ResourceSet(ResourceSet resourceSet) {
        super();
        this.resources = new HashMap<>(resourceSet.resources);
        //TODO CHECK CLONE!
    }

    private Iterator<Map.Entry<Resource, Integer>> getIterator(ResourceSet resourceSet) {
        Set<Map.Entry<Resource, Integer>> newRes = resourceSet.resources.entrySet();
        return newRes.iterator();
    }

    /**
     * Questo fa X
     * @param resource
     * @param amount
     */

    public void variateResource(Resource resource, int amount) {

        Integer myQty = this.resources.get(resource);
        if (myQty == null)
            this.resources.put(resource, amount);
        else {
            int res = myQty.intValue() + amount;
            this.resources.replace(resource, res);
        }
    }

    /**
     * Adds or subtract an entire ResourceSet
     * @param resourceSet
     */
    public void variateResource(ResourceSet resourceSet) {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(resourceSet);

        while (i.hasNext()) {
            Map.Entry<Resource, Integer> entry = i.next();
            this.variateResource(entry.getKey(), entry.getValue());
        }

    }

    /**
     * Can I find in the ResourceSet all resources and amounts of the ResourceSet as parameter
     * @param resourceSet
     * @return
     */
    public boolean isIncluded(ResourceSet resourceSet) {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(resourceSet);

        while (i.hasNext()) {
            Map.Entry<Resource, Integer> entry = i.next();
            if (!isIncluded(entry.getKey(), entry.getValue()))
                return false;
        }
        return true;
    }

    /**
     * Can I find at least one Resource
     * @param resource
     * @return
     */
    public boolean isIncluded(Resource resource) {
        for(Resource res : this.getResources().keySet()){
            if(res.equals(resource)){
                return true;
            }
        }

        return false;

    }

    /**
     * Can I find in the set at least a certain @amount of Resouce
     * @param resource
     * @param amount the amount
     * @return
     */
    public boolean isIncluded(Resource resource, int amount) {
        Integer myQty = this.resources.get(resource);
        if (myQty == null) {
            return amount >= 0;
        }
        return (myQty.intValue() + amount) >= 0;
    }

    /**
     * This ResourceSet contains at least negative Resource?
     * @return
     */
    public boolean isNegativeValuePresent() {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(this);
        while (i.hasNext()) {
            Map.Entry<Resource, Integer> entry = i.next();
            if (entry.getValue().intValue() < 0)
                return true;
        }
        return false;
    }

    /**
     * This ResourceSet contains at least positive Resource
     * @return
     */
    public boolean isPositiveValuePresent() {
        Iterator<Map.Entry<Resource, Integer>> i = getIterator(this);
        while (i.hasNext()) {
            Map.Entry<Resource, Integer> entry = i.next();
            if (entry.getValue().intValue() >= 0)
                return true;
        }
        return false;
    }

    /**
     * Gives the quantity of a certain Resource
     * @param resource
     * @return
     */
    public int getResourceAmount(Resource resource)
    {
        Integer amount = this.resources.get(resource);
        if (amount==null)
            return 0;
        return amount.intValue();
    }

    /**
     * Gives the quantity of all resources
     * @return
     */
    public int totalResourceQuantity(){

        int totalResource = 0;

        for(Resource resource : this.resources.keySet()){
            totalResource = totalResource + this.resources.get(resource);
        }

        return totalResource;
    }

    public HashMap<Resource, Integer> getResources() {
        return resources;
    }

    public boolean isEmpty(){
        return this.getResources().keySet().size() == 0;
    }

}


