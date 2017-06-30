package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/26/17.
 */
public class PayCardTest {


    private Game game;
    private Player player;
    private PayCard payCard;
    private BoardActionOnTower boardActionOnTower;
    private BoardActionOnTower boardActionOnTower1;

    private FamilyMember familyMember;
    private FamilyMember familyMember1;

    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());

        player = game.getGameStatus().getPlayers().get("peppe");

        Tower tower = game.getBoard().getTowers().get("YELLOW");
        familyMember = player.getFamilyMembers()[3];
        familyMember1 = player.getFamilyMembers()[0];
        familyMember.setValue(10);
        familyMember1.setValue(15);
        boardActionOnTower = new BoardActionOnTower(player,0,tower,familyMember,game);
        boardActionOnTower1 = new BoardActionOnTower(player,1,tower,familyMember,game);
        payCard = new PayCard(tower,1,player,game);

    }


    @Test
    public void plainTest() throws InterruptedException {

        payCard.execute();
        assertTrue(player.getPlayerBoard().getDevelopmentCards("YELLOW").size() == 1);

    }

    @Test
    public void payTowerPenality() throws InterruptedException {


        boardActionOnTower.execute();
        game.endTurn();
        boardActionOnTower1.execute();


    }
}
