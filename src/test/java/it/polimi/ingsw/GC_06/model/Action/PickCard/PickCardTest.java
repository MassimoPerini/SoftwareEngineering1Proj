package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by massimo on 06/06/17.
 */
public class PickCardTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        game.addPlayer("massimo");
        game.addPlayer("pinco");
        game.start();

    }


    @Test
    public void pickCard()
    {
    /*    Player player = game.getGameStatus().getCurrentPlayer();
        Board board = game.getBoard();
        DevelopmentCard pickingCard = board.getTowers().get(0).getTowerFloor().get(0).getCard();
        Action pickCard = new PickCard(player,board.getTowers().get(0), board.getTowers().get(0).getTowerFloor().get(0), 10);
        assertTrue(player.getPlayerBoard().getDevelopmentCards().size() == 1);
        assertTrue(player.getPlayerBoard().getDevelopmentCards().get(0) == pickingCard);
        */
    }



    @After
    public void tearDown() throws Exception {

    }

}