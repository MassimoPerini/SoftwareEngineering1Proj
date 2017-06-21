package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 20/06/17.
 */
public class PlayerBonusActions {

    private List<ResourceSet> parchmentList;
    private Map<String, List<Integer>>  pickAnotherCard;
    private List<ResourceSet> requirementCard;
    private Map<String, List<Integer>> prodHarvAsk;

    public PlayerBonusActions()
    {
        parchmentList = new LinkedList<>();
        pickAnotherCard = new HashMap<>();
        requirementCard = new LinkedList<>();
    }

    public void changeParchment(List<ResourceSet> parchmentList)
    {
        this.parchmentList = parchmentList;
    }

    public void setPickAnotherCard(Map<String, List<Integer>> clientTowerFloors)
    {
        this.pickAnotherCard = clientTowerFloors;
    }

    public void setRequirementCard(List<ResourceSet> requirementCard) {
        this.requirementCard = requirementCard;
    }

    public void setProdHarvAsk(Map<String, List<Integer>> cards) {
        this.prodHarvAsk = cards;
    }
}