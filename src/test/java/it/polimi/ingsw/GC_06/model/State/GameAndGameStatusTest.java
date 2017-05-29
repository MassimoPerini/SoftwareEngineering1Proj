package it.polimi.ingsw.GC_06.model.State;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by massimo on 28/05/17.
 */
public class GameAndGameStatusTest {
    @Before
    public void setUp() throws Exception {
        //TODO to remove
        Game.clearForTesting();
    }

    @Test
    public void checkGameSingleton() throws Exception {
        Game game = Game.getInstance();
        Game game1 = Game.getInstance();
        assertTrue(game == game1);
    }


    @Test (expected=NullPointerException.class)
    public void checkNullPlayer() throws IOException {
        Game game = Game.getInstance();
        game.addPlayer(null);
    }

    @Test (expected = IllegalStateException.class)
    public void reachedMaxNumberOfPlayer() throws IOException {
        Game game = Game.getInstance();
        game.addPlayer("pinco");
        game.addPlayer("pallino");
        game.addPlayer("massimo");
    }

    @Test (expected = IllegalStateException.class)
    public void reachedMaxCustomNumberOfPlayer() throws IOException {
        Game game = Game.getInstance();
        game.setMaxPlayers(3);
        game.addPlayer("sudo");
        game.addPlayer("nano");
        game.addPlayer("massimo");
        game.addPlayer("perini");
    }

    @Test
    public void testAdded() throws IOException {
        Game game = Game.getInstance();
        game.setMaxPlayers(5);
        game.addPlayer("pinco");
        game.addPlayer("pallino");
        game.addPlayer("massimo");
        game.addPlayer("perini");
        assertEquals(Game.getInstance().getGameStatus().getPlayers().size(), 4);
        assertEquals(Game.getInstance().getGameStatus().getPlayers().get(2).getPLAYER_ID(), "massimo");
    }

    @Test
    public void testFirst() throws IOException {
        Game game = Game.getInstance();
        game.setMaxPlayers(3);
        game.addPlayer("pinco");
        game.addPlayer("pallino");
        game.addPlayer("massimo");
        assertEquals(Game.getInstance().getGameStatus().getCurrentPlayer().getPLAYER_ID(), "pinco");
    }

    @Test (expected=IllegalStateException.class)
    public void testAddedAndRemoved() throws IOException {
        Game game = Game.getInstance();
        game.setMaxPlayers(3);
        game.addPlayer("pinco");
        game.addPlayer("pallino");
        game.addPlayer("massimo");
        game.setMaxPlayers(2);
    }



}