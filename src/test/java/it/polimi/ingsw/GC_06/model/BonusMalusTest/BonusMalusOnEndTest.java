package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnEnd;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 7/2/17.
 */
public class BonusMalusOnEndTest {



    private BonusMalusOnEnd bonusMalusOnEnd;
    private Game game;
    private Player player;



    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());
        player = game.getGameStatus().getPlayers().get("peppe");
        List<Resource> resources = new LinkedList<>();
        resources.add(Resource.MONEY);
        resources.add(Resource.MILITARYPOINT);
        bonusMalusOnEnd = new BonusMalusOnEnd(Resource.FAITHPOINT,resources, ActionType.END_GAME,-1,true);

    }

    @Test
    public void firstTest(){

        bonusMalusOnEnd.modify(player,player.getResourceSet());
        assertTrue(bonusMalusOnEnd.isAllowed(player.getResourceSet()));
        assertTrue(player.getResourceSet().getResourceAmount(Resource.FAITHPOINT) == 0);
        assertTrue(bonusMalusOnEnd.check(ActionType.END_GAME,player.getResourceSet()));
    }

}
