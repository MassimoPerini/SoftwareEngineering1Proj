package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/2/17.
 */
public class PlayerBonusActionsTest {

    private PlayerBonusActions playerBonusActions;

    @Before
    public void setUp(){

        playerBonusActions = new PlayerBonusActions();

    }

    @Test
    public void changePowerUp() throws Exception {
        playerBonusActions.changePowerUp(false);
        assertTrue(playerBonusActions.getCurrentState().equals(ClientStateName.POWERUP));
    }

    @Test
    public void changeParchment() throws Exception {

        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY,10);
        List<ResourceSet> resourceSets = new LinkedList<>();
        resourceSets.add(resourceSet);
        playerBonusActions.changeParchment(resourceSets);
        assertTrue(playerBonusActions.getCurrentState().equals(ClientStateName.PARCHMENT));

    }

    @Test
    public void setPickAnotherCard() throws Exception {

        ClientTowerFloor clientTowerFloor;
        clientTowerFloor = new ClientTowerFloor();
        List<ClientTowerFloor> towerFloors = new LinkedList<>();
        towerFloors.add(clientTowerFloor);
        playerBonusActions.setPickAnotherCard(towerFloors);
        assertTrue(playerBonusActions.getCurrentState().equals(ClientStateName.CHOOSE_NEW_CARD));


    }

    @Test
    public void setRequirementCard() throws Exception {

        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY,10);
        Requirement requirement = new Requirement(resourceSet,resourceSet);
        List<Requirement> requirements = new LinkedList<>();
        requirements.add(requirement);
        playerBonusActions.setRequirementCard(requirements);
        assertTrue(playerBonusActions.getCurrentState().equals(ClientStateName.MULTIPLE_PAYMENT));

    }

    @Test
    public void setProdHarvAsk() throws Exception {

        Map<String,List<Integer>> cards = new HashMap<>();
        playerBonusActions.setProdHarvAsk(cards);
        assertTrue(playerBonusActions.getCurrentState().equals(ClientStateName.ASK_PRODHARV_CARDS));

    }



}