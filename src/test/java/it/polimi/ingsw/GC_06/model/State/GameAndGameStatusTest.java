package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by massimo on 28/05/17.
 */
public class GameAndGameStatusTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        //TODO to remove
        game = new Game();
    }

    @Test
    public void checkGameSingleton() throws Exception {
        Game game = Game.getInstance();
        Game game1 = Game.getInstance();
        assertTrue(game == game1);
    }


    @Test (expected=NullPointerException.class)
    public void checkNullPlayer() throws IOException {
        game.addPlayer(null);
    }
    

    @Test (expected = IllegalStateException.class)
    public void reachedMaxNumberOfPlayer() throws IOException {
        game.addPlayer("sudo");
        game.addPlayer("nano");
        game.addPlayer("massimo");
        game.addPlayer("perini");
        game.addPlayer("pallo");
    }

    @Test
    public void testAdded() throws IOException {
        game.addPlayer("pinco");
        game.addPlayer("pallino");
        game.addPlayer("massimo");
        game.addPlayer("perini");
        assertTrue(game.getGameStatus().getPlayers().size() == 4);
        assertNotEquals(game.getGameStatus().getPlayers().get("massimo"), null);
        assertEquals(game.getGameStatus().getPlayers().get("pallino").getPLAYER_ID(), "pallino");

    }

    @Test
    public void testFirst() throws IOException {
        game.addPlayer("pinco");
        game.addPlayer("pallino");
        game.addPlayer("massimo");
        game.start();
        int i=0;
        Player currentPlayer = Game.getInstance().getCurrentPlayer();

        if (currentPlayer.getPLAYER_ID().equals("pinco")) i++;
        if (currentPlayer.getPLAYER_ID().equals("pallino")) i++;
        if (currentPlayer.getPLAYER_ID().equals("massimo")) i++;

        assertTrue(i==1);
    }



}