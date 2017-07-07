package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;

import java.io.IOException;

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
        game.addPlayer("peppe");
        game.addPlayer("massi");
        game.start(new DefaulEventManagerFake());

        player = game.getGameStatus().getPlayers().get("peppe");
        player1 = game.getGameStatus().getPlayers().get("massi");

        roundManager = new RoundManager(game.getBoard(),4);


    }
    /**
    @Test

    public void endTurnTest(){

        roundManager.endTurn();
    }*/

 /**   @Test

    public void startTest(){

        roundManager.addPlayer(player);
        int  originalQuantityresourceSet = player.getResourceSet().totalResourceQuantity();
        roundManager.start();

       // assertTrue(player.getResourceSet().totalResourceQuantity() == 2*originalQuantityresourceSet);
      //  assertFalse(board.getTowers().isEmpty());
    }

    @Test
    public void endTurnTest(){
        roundManager.addPlayer(player);
        //roundManager.addPlayer(player1);
        int oldTurn = roundManager.getTurn();
        assertTrue(roundManager.getCurrentPlayer().getPLAYER_ID().equals("peppe"));
        roundManager.endTurn();
       // assertTrue(roundManager.getCurrentPlayer().getPLAYER_ID().equals("massi"));
        assertTrue(oldTurn == roundManager.getTurn()-1);
    }*/

}
