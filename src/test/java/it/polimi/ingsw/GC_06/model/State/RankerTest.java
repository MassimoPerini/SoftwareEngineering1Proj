package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 01/07/2017.
 */
public class RankerTest {
    private Map<String, Player> players;
    private Game game;
    private GameStatus gameStatus;

    @Before
    public void SetUp() throws IOException {
        game = new Game(1);
        game.addPlayer("player1");
        game.addPlayer("player2");
        game.addPlayer("player3");
        game.addPlayer("player4");
    }

    @Test
    public void TestResource1() throws IOException {
        ResourceSet resourceSet1 = new ResourceSet();
        ResourceSet resourceSet2 = new ResourceSet();
        ResourceSet resourceSet3 = new ResourceSet();
        ResourceSet resourceSet4 = new ResourceSet();
        resourceSet1.variateResource(Resource.MONEY, 5);
        resourceSet2.variateResource(Resource.MONEY, 3);
        resourceSet3.variateResource(Resource.MONEY, 4);
        resourceSet4.variateResource(Resource.MONEY, 6);
        List<ResourceSet> resourceSets = new ArrayList<>();
        resourceSets.add(resourceSet1);
        resourceSets.add(resourceSet2);
        resourceSets.add(resourceSet3);
        resourceSets.add(resourceSet4);
        gameStatus = game.getGameStatus();
        players = gameStatus.getPlayers();
        int i = 0;
        for (Player player : players.values()) {
            player.variateResource(resourceSets.get(i));
            i++;
        }
        Ranker ranker = new Ranker(gameStatus);
        List<Player> rankedPlayers = ranker.rankByResource(Resource.MONEY);

        assertTrue(rankedPlayers.get(0).getPLAYER_ID() == "player4");
        assertTrue(rankedPlayers.get(1).getPLAYER_ID() == "player1");
        assertTrue(rankedPlayers.get(2).getPLAYER_ID() == "player3");
        assertTrue(rankedPlayers.get(3).getPLAYER_ID() == "player2");

    }

    @Test
    public void TestResource2() throws IOException {
        ResourceSet resourceSet1 = new ResourceSet();
        ResourceSet resourceSet2 = new ResourceSet();
        ResourceSet resourceSet3 = new ResourceSet();
        ResourceSet resourceSet4 = new ResourceSet();
        resourceSet1.variateResource(Resource.VICTORYPOINT, 6);
        resourceSet2.variateResource(Resource.VICTORYPOINT, 5);
        resourceSet3.variateResource(Resource.VICTORYPOINT, 4);
        resourceSet4.variateResource(Resource.VICTORYPOINT, 3);
        List<ResourceSet> resourceSets = new ArrayList<>();
        resourceSets.add(resourceSet1);
        resourceSets.add(resourceSet2);
        resourceSets.add(resourceSet3);
        resourceSets.add(resourceSet4);
        gameStatus = game.getGameStatus();
        players = gameStatus.getPlayers();
        int i = 0;
        for (Player player : players.values()) {
            player.variateResource(resourceSets.get(i));
            i++;
        }
        Ranker ranker = new Ranker(gameStatus);
        List<Player> rankedPlayers = ranker.rankByResource(Resource.VICTORYPOINT);

        assertTrue(rankedPlayers.get(0).getPLAYER_ID() == "player1");
        assertTrue(rankedPlayers.get(1).getPLAYER_ID() == "player2");
        assertTrue(rankedPlayers.get(2).getPLAYER_ID() == "player3");
        assertTrue(rankedPlayers.get(3).getPLAYER_ID() == "player4");

    }
}
