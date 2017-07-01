package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/27/17.
 */
public class PickCardTest {

    private Game game;
    private Player player;
    private PickCard pickCard;

    @Before
    public void setUp() throws Exception {
        game = new Game(1);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());




        player = game.getGameStatus().getPlayers().get("peppe");
        Tower tower = game.getBoard().getTowers().get("YELLOW");

        pickCard = new PickCard(player,tower,0,game);
    }


    @Test
    public void pickCard() throws InterruptedException {

        assertTrue(pickCard.isAllowed());


        pickCard.execute();
        assertTrue(player.getPlayerBoard().getDevelopmentCards().size() == 1);
    }
}
