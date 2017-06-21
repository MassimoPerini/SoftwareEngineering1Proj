package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 20/06/17.
 */
public class PlayerBonusActions {

    private List<ResourceSet> parchmentList;
    private List<ClientTowerFloor> pickAnotherCard;
    private List<ResourceSet> requirementCard;
    private List<String> prodHarvAsk;

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

    public void setRequirementCard(List<ResourceSet> requirementCard) {
        this.requirementCard = requirementCard;
    }

    public void setProdHarvAsk(List<String> prodHarvAsk) {
        this.prodHarvAsk = prodHarvAsk;
    }
}
