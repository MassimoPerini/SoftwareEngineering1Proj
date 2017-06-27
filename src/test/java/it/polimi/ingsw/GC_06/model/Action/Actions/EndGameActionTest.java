package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by giuseppe on 6/26/17.
 */
public class EndGameActionTest {

    private Game game;
    private Player player;
    private BoardActionOnTower boardActionOnTower;
    private BoardActionOnTower boardActionOnTower1;
    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaultEventManager(new ServerOrchestrator(),game));
        player = game.getGameStatus().getPlayers().get("peppe");

        Tower tower = game.getBoard().getTowers().get("BLUE");
        FamilyMember familyMember = player.getFamilyMembers()[0];
        FamilyMember familyMember1 = player.getFamilyMembers()[1];


        boardActionOnTower = new BoardActionOnTower(player,0,tower,familyMember,game);
        boardActionOnTower1 = new BoardActionOnTower(player,1,tower,familyMember1,game);

    }


    @Test
    public void test(){

       boardActionOnTower.run();
       boardActionOnTower1.run();
    }
}

