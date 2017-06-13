package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Effect.EffectOnResourcesHarvProduction;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 08/06/2017.
 */
public class EffectOnResourcesHarvProductionTest {
    private Player player;
    private EffectOnResourcesHarvProduction effectOnResourcesHarvProduction;
    private ResourceSet resourceSetPlayer;

    @Before
    public void setUp() throws IOException {
        Game game = new Game();
        game.addPlayer("gabriele");
        player = game.getGameStatus().getPlayers().get("gabriele");
        resourceSetPlayer = new ResourceSet();
        resourceSetPlayer.variateResource(Resource.MONEY,20);
        resourceSetPlayer.variateResource(Resource.WOOD, 20);
        resourceSetPlayer.variateResource(Resource.SERVANT, 20);
        resourceSetPlayer.variateResource(Resource.STONE, 20);
        resourceSetPlayer.variateResource(Resource.FAITHPOINT, 20);
        resourceSetPlayer.variateResource(Resource.MILITARYPOINT,20);
        player.variateResource(resourceSetPlayer);

    }

    @Test
    public void correctPoints() {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY, 2);
        resourceSet.variateResource(Resource.WOOD, 2);
        int requiredPoints = 20;
        int points = 22;
        effectOnResourcesHarvProduction = new EffectOnResourcesHarvProduction(requiredPoints, resourceSet);
        effectOnResourcesHarvProduction.execute(player,points);
        ResourceSet r = player.getResourceSet();
        assertTrue(r.getResourceAmount(Resource.MONEY)==22);
        assertTrue(r.getResourceAmount(Resource.WOOD)==22);
    }

    @Test
    public void correctPointsNegative() {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY, -2);
        resourceSet.variateResource(Resource.WOOD, -2);
        int requiredPoints = 20;
        int points = 22;
        effectOnResourcesHarvProduction = new EffectOnResourcesHarvProduction(requiredPoints, resourceSet);
        effectOnResourcesHarvProduction.execute(player,points);
        ResourceSet r = player.getResourceSet();
        assertTrue(r.getResourceAmount(Resource.MONEY)==18);
        assertTrue(r.getResourceAmount(Resource.WOOD)==18);
    }

    @Test //(expected = IllegalArgumentException.class) //TODO perchè sull'effetto risorse semplice ho l'IllegalArgument e qui no?
    public void correctPointsLessThanZero() {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY, -21);
        resourceSet.variateResource(Resource.WOOD, -21);
        int requiredPoints = 20;
        int points = 22;
        effectOnResourcesHarvProduction = new EffectOnResourcesHarvProduction(requiredPoints, resourceSet);
        effectOnResourcesHarvProduction.execute(player,points);
        ResourceSet r = player.getResourceSet();
        assertTrue(r.getResourceAmount(Resource.MONEY)==-1);
        assertTrue(r.getResourceAmount(Resource.WOOD)==-1);
    }

    @Test //TODO il test mostra che non esegue, ma come ho scritto nella classe dovremmo mettere almeno un avviso
    public void insufficientPoints() {
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY, -2);
        resourceSet.variateResource(Resource.WOOD, -2);
        int requiredPoints = 20;
        int points = 10;
        effectOnResourcesHarvProduction = new EffectOnResourcesHarvProduction(requiredPoints, resourceSet);
        effectOnResourcesHarvProduction.execute(player,points);
        ResourceSet r = player.getResourceSet();
        assertTrue(r.getResourceAmount(Resource.MONEY)==20);
        assertTrue(r.getResourceAmount(Resource.WOOD)==20);
    }
}
