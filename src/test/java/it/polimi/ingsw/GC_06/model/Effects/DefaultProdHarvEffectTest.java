package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gabri on 08/06/2017.
 */
public class DefaultProdHarvEffectTest {
    private Player player;
    private ResourceSet resourceSetPlayer;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");

        Game game = new Game(1);
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
    public void correctExecution() {
        ResourceSet requirementsResource = new ResourceSet();
        requirementsResource.variateResource(Resource.MONEY,5);
        requirementsResource.variateResource(Resource.WOOD,5);
        ResourceSet requirementCost = new ResourceSet();
        requirementCost.variateResource(Resource.SERVANT,4);
        requirementCost.variateResource(Resource.MONEY,5);
        Requirement requirement = new Requirement(requirementsResource, requirementCost);
        ResourceSet resourcesEffect = new ResourceSet();
        resourcesEffect.variateResource(Resource.STONE, 5);
        resourcesEffect.variateResource(Resource.FAITHPOINT, 5);
        EffectOnResources effectOnResources = new EffectOnResources(resourcesEffect);
        int minPoints = 3;
        //defaultProdHarvEffect = new DefaultProdHarvEffect(requirement, effectOnResources, minPoints);
        //TODO capire perche non mi prende questi parametri
    }
}
