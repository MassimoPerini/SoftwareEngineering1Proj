package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/1/17.
 */
public class EffectOnConditionsTest {

    private Game game;
    private Player player;
    private EffectOnConditions effectOnConditions;

    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());
        player = game.getGameStatus().getPlayers().get("peppe");

        effectOnConditions = new EffectOnConditions(Resource.MILITARYPOINT,5,Resource.MONEY,null);
        assertTrue(600 == player.getResourceSet().getResourceAmount(Resource.MONEY));

    }


    @Test
    public void firstTest(){


        effectOnConditions.execute(player,game);
    }

}