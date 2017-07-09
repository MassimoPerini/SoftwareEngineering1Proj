package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 7/1/17.
 */
public class EffectOnConditionsTest {

    private Game game;
    private Player player;
    private EffectOnConditions effectOnConditions;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.addPlayer("peppe");
        game.init();
        player = game.getGameStatus().getPlayers().get("peppe");
        ResourceSet playerResources = new ResourceSet();
        playerResources.variateResource(Resource.MILITARYPOINT, 5);
        player.variateResource(playerResources);

        effectOnConditions = new EffectOnConditions(Resource.MILITARYPOINT,2,Resource.MONEY,null);


    }


    @Test
    public void firstTest(){


        effectOnConditions.execute(player,game);
        assertTrue(10 == player.getResourceSet().getResourceAmount(Resource.MONEY));
    }

}