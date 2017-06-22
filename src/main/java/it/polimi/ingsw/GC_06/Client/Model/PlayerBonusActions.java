package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 20/06/17.
 */
public class PlayerBonusActions {

    private List<ResourceSet> parchmentList;
    private List<ClientTowerFloor>  pickAnotherCard;
    private List<Requirement> requirementCard;
    private Map<String, List<Integer>> prodHarvAsk;

    public PlayerBonusActions()
    {
        parchmentList = new LinkedList<>();
        pickAnotherCard = new LinkedList<>();
        requirementCard = new LinkedList<>();
    }

    public void changeParchment(List<ResourceSet> parchmentList)
    {
        this.parchmentList = parchmentList;
    }

    public void setPickAnotherCard(List<ClientTowerFloor> clientTowerFloors)
    {
        this.pickAnotherCard = clientTowerFloors;
    }

    public void setRequirementCard(List<Requirement> requirementCard) {
        this.requirementCard = requirementCard;
    }

    public void setProdHarvAsk(Map<String, List<Integer>> cards) {
        this.prodHarvAsk = cards;
    }

    public List<ResourceSet> getParchmentList() {
        return parchmentList;
    }

    public List<ClientTowerFloor> getPickAnotherCard() {
        return pickAnotherCard;
    }

    public List<Requirement> getRequirementCard() {
        return requirementCard;
    }

    public Map<String, List<Integer>> getProdHarvAsk() {
        return prodHarvAsk;
    }
}
