package it.polimi.ingsw.GC_06.model.Effects;


import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import jdk.nashorn.internal.runtime.RewriteException;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.ingsw.GC_06.model.Resource.Resource.*;
import static org.junit.Assert.assertTrue;


/**
 * Created by gabri on 03/06/2017.
 */
public class EffectOnResourcesTest {
    private Player player;
    private ResourceSet resourceSet;

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

        player.variateResource(resourceSet);
    }

    @Test
    public void correctPositiveTest() {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(MONEY, 2);
        resourceSet.variateResource(WOOD, 10);
        EffectOnResources effectOnResources = new EffectOnResources(resourceSet);

        effectOnResources.execute(player);
        ResourceSet r = player.getResourceSet();
        assertTrue(r.getResourceAmount(MONEY) == 14);
        assertTrue(r.getResourceAmount(WOOD) == 25);
    }

    @Test
    public void correctNegativeTest() {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(SERVANT, -3);
        resourceSet.variateResource(STONE, -10);
        EffectOnResources effectOnResources2 = new EffectOnResources(resourceSet);

        effectOnResources2.execute(player);
        ResourceSet r = player.getResourceSet();
        assertTrue(r.getResourceAmount(SERVANT) == 7);
        assertTrue(r.getResourceAmount(STONE) == 10);
    }

    @Test
    public void lessThanZero() {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(MONEY, 16);
        resourceSet.variateResource(WOOD, 28);
        EffectOnResources effectOnResources3 = new EffectOnResources(resourceSet);

        effectOnResources3.execute(player);
        ResourceSet r = player.getResourceSet();
     //   assertTrue(r.getResourceAmount(MONEY) == 0);
     //   assertTrue(r.getResourceAmount(WOOD) == 0);
    }

}
