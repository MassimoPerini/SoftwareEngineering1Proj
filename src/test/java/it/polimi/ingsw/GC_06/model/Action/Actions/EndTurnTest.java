package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 02/07/2017.
 */
public class EndTurnTest {
    private Game game;
    private EndTurn endTurn;

    @Before
    public void SetUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.init();
        game.addPlayer("gabri");
        game.addPlayer("peppe");

    }

    @Test
    public void FirstTest() throws IOException {
        endTurn = new EndTurn(game);
        endTurn.execute();
        assertTrue(game.getCurrentPlayer().getPLAYER_ID()=="peppe");
        endTurn.execute();
        assertTrue(game.getCurrentPlayer().getPLAYER_ID()=="gabri");
    }
}
