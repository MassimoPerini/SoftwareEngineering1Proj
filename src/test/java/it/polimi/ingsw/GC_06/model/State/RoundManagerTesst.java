package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 7/2/17.
 */
public class RoundManagerTesst {

    private Board board;
    Game game;
    private RoundManager roundManager;
    private Player player;
    private Player player1;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");

        game = new Game(0);
        game.init();
        game.addPlayer("peppe");
        game.addPlayer("massi");
        //game.start(new DefaulEventManagerFake());

        player = game.getGameStatus().getPlayers().get("peppe");
        player1 = game.getGameStatus().getPlayers().get("massi");

        roundManager = new RoundManager(15);


    }

    @Test

    public void endTurnTest(){
        roundManager.addPlayer(player);
        roundManager.endTurn();
    }

    /*@Test
    public void startTest() {
        roundManager.addPlayer(player);
        ResourceSet pre = player.getResourceSet();
        roundManager.start();
        ResourceSet post = player.getResourceSet();
        assertTrue(!(pre.equals(post)));

    }*/


}
