package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/2/17.
 */
public class ControllerGameTest {

    private Game game;
    private ControllerGame controllerGame;

    @Test(expected = NullPointerException.class)
    public void start() throws Exception {
        game = new Game(0);
        game.addPlayer("peppe");
        game.addPlayer("massi");
        controllerGame = new ControllerGame(game,new ServerOrchestrator(),0);
        controllerGame.start();

    }

    @Test
    public void stop() throws Exception {
    }

}