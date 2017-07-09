package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/26/17.
 */
/**
public class EndGameActionTest {

    private Game game;
    private Player player;
    private BoardActionOnTower boardActionOnTower;
    private BoardActionOnTower boardActionOnTower1;
    private EndGameMap endGame;
    private EndGameAction endGameAction;
    private List<Player> players;
    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.init();
        game.start(new DefaulEventManagerFake());
        game.addPlayer("peppe");
        player = game.getGameStatus().getPlayers().get("peppe");

        Tower tower = game.getBoard().getTowers().get("PURPLE");
        FamilyMember familyMember = player.getFamilyMembers()[0];
        FamilyMember familyMember1 = player.getFamilyMembers()[1];


        boardActionOnTower = new BoardActionOnTower(player,0,tower,familyMember,game);
        boardActionOnTower1 = new BoardActionOnTower(player,1,tower,familyMember1,game);

        String colour = "PURPLE";
        Integer[] array = {3,4,5,6,7};

        List<Integer> pointsList = new LinkedList<>();
        pointsList.addAll(Arrays.asList(array));

        Map<String,List<Integer>> endGameMap = new HashMap<>();
        endGameMap.put(colour,pointsList);

        endGame = new EndGameMap(endGameMap);

         players = new LinkedList<>();

        for (String s : game.getGameStatus().getPlayers().keySet()) {

            players.add(game.getGameStatus().getPlayers().get(s));
        }

        endGameAction = new EndGameAction(players, Resource.FAITHPOINT,game);



    }




    @Test
    public void turnCardsIntoPointsTest() throws InterruptedException {

        boardActionOnTower.execute();
        game.endTurn();
       boardActionOnTower1.execute();

       int oldFaithPointQuantity = player.getResourceSet().getResourceAmount(Resource.FAITHPOINT);

        endGameAction.turnCardsIntoPoints(player);

        assert (player.getResourceSet().getResourceAmount(Resource.FAITHPOINT) == oldFaithPointQuantity + 4);
    }


    @Test
    public void turnResourceIntoPointsTest(){

        int oldResourceQuantity = player.getResourceSet().getResourceAmount(Resource.FAITHPOINT);
        int originalResource =  player.getResourceSet().totalResourceQuantity() - player.getResourceSet().getResourceAmount(Resource.FAITHPOINT);
        endGameAction.turnResourceIntoPoint(player);

        assertTrue(player.getResourceSet().getResourceAmount(Resource.FAITHPOINT) == oldResourceQuantity + originalResource*0.5);

    }

    @Test

    public void addFinal() throws InterruptedException {
        boardActionOnTower.execute();
        game.endTurn();
        boardActionOnTower1.execute();
        int oldQuantity = player.getResourceSet().getResourceAmount(Resource.FAITHPOINT);
        int addQuantity = player.getAddAtTheEnd().getResourceAmount(Resource.FAITHPOINT);


        endGameAction.addFinalPoint(player);

        assertTrue(player.getResourceSet().getResourceAmount(Resource.FAITHPOINT) == oldQuantity+addQuantity);

    }

}
*/