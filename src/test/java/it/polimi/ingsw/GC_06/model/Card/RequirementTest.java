package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 02/07/2017.
 */
public class RequirementTest {
    private List<Requirement> requirements;
    private Game game;
    private Player player;

    @Before
    public void SetUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        ResourceSet requirement1 = new ResourceSet();
        ResourceSet cost1 = new ResourceSet();
        ResourceSet requirement2 = new ResourceSet();
        ResourceSet cost2 = new ResourceSet();
        ResourceSet requirement3 = new ResourceSet();
        ResourceSet cost3 = new ResourceSet();

        requirement1.variateResource(Resource.WOOD, -2);
        requirement1.variateResource(Resource.MONEY, -1);
        cost1.variateResource(Resource.VICTORYPOINT, -2);
        requirement2.variateResource(Resource.MONEY, -1000);
        cost2.variateResource(Resource.FAITHPOINT, -1);
        requirement3.variateResource(Resource.STONE, -10);
        cost3.variateResource(Resource.SERVANT, -1000);
        Requirement r1 = new Requirement(requirement1, cost1);
        Requirement r2 = new Requirement(requirement2, cost2);
        Requirement r3 = new Requirement(requirement3, cost3);
        requirements = new ArrayList<>();
        requirements.add(r1);
        requirements.add(r2);
        requirements.add(r3);



        game = new Game(0);
        game.addPlayer("gabri");
        player = game.getGameStatus().getPlayers().get("gabri");
        player.getResourceSet().variateResource(Resource.MONEY, 20);
        player.getResourceSet().variateResource(Resource.WOOD, 20);
        player.getResourceSet().variateResource(Resource.STONE, 20);
        player.getResourceSet().variateResource(Resource.SERVANT, 20);
        player.getResourceSet().variateResource(Resource.MILITARYPOINT, 20);
        player.getResourceSet().variateResource(Resource.FAITHPOINT, 20);
        player.getResourceSet().variateResource(Resource.VICTORYPOINT, 20);

    }

    @Test
    public void everythingWorking() throws IOException {
        ResourceSet confrontation = player.getResourceSet();
        assertTrue(requirements.get(0).isSatisfied(confrontation));
        assertFalse(requirements.get(1).isSatisfied(confrontation));
        assertFalse(requirements.get(2).isSatisfied(confrontation));


        /*List<Resource> l = new ArrayList<>();
        l.add(Resource.MONEY);
        l.add(Resource.MILITARYPOINT);
        l.add(Resource.FAITHPOINT);
        l.add(Resource.WOOD);
        l.add(Resource.STONE);
        l.add(Resource.SERVANT);
        int i;
        for (i=0; i<l.size(); i++) {
            System.out.print(player.getResourceSet().getResourceAmount(l.get(i)));
        }*/
    }

    @Test
    public void correctpayment() throws IOException {
        Requirement requirement = requirements.get(0);
        requirement.doIt(player);
        assertTrue(player.getResourceSet().getResourceAmount(Resource.VICTORYPOINT)==18);
    }

    @Test(expected = IllegalArgumentException.class)
    public void incorrectPayment() throws IOException {
        Requirement requirement = requirements.get(2);
        requirement.doIt(player);
    }


}
