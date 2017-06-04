package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Optional;

import static it.polimi.ingsw.GC_06.model.Resource.Resource.*;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by gabri on 03/06/2017.
 */
public class EffectOnResourcesTest {
    private EffectOnResources effectOnResources, effectOnResources2,effectOnResources3;
    private Player player;
    private ResourceSet resourceSet,resourceSet2,resourceSet3,resourceSet4;

    @Before
    public void setUp() {
        Game.clearForTesting();
        Game.getInstance().addPlayer("gabriele");
        player = Game.getInstance().getGameStatus().getCurrentPlayer();
        resourceSet = new ResourceSet();
        resourceSet.variateResource(MONEY, 12);
        resourceSet.variateResource(Resource.FAITHPOINT, 2);
        resourceSet.variateResource(WOOD, 15);
        resourceSet.variateResource(Resource.SERVANT, 10);
        resourceSet.variateResource(Resource.STONE, 20);
        resourceSet2 = new ResourceSet();
        resourceSet2.variateResource(MONEY, 2);
        resourceSet2.variateResource(WOOD, 10);
        effectOnResources = new EffectOnResources(resourceSet2);
        resourceSet3 = new ResourceSet();
        resourceSet3.variateResource(SERVANT, -3);
        resourceSet3.variateResource(STONE, -10);
        effectOnResources2 = new EffectOnResources(resourceSet3);
        resourceSet3 = new ResourceSet();
        resourceSet3.variateResource(MONEY, 16);
        resourceSet3.variateResource(WOOD, 28);
        effectOnResources3 = new EffectOnResources(resourceSet3);
        player.variateResource(resourceSet);
    }

    @Test
    public void correctPositiveTest() {
        effectOnResources.execute(player);
        ResourceSet r = player.getResourceSet();
        HashMap<Resource, Integer> finalResources = r.getResources();
        assertTrue(finalResources.get(MONEY) == 14);
        assertTrue(finalResources.get(WOOD) == 25);
    }

    @Test
    public void correctNegativeTest() {
        effectOnResources2.execute(player);
        ResourceSet r = player.getResourceSet();
        HashMap<Resource, Integer> finalResources = r.getResources();
        assertTrue(finalResources.get(SERVANT) == 7);
        assertTrue(finalResources.get(STONE) == 10);
    }

    @Test
    public void lessThanZero() {
        effectOnResources3.execute(player);
        ResourceSet r = player.getResourceSet();
        HashMap<Resource,Integer> finalResource = r.getResources();
        assertTrue(finalResource.get(MONEY) == 0);
        assertTrue(finalResource.get(WOOD) == 0);
    }

}
