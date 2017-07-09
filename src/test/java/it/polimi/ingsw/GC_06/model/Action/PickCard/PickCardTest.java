package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/27/17.
 */

public class PickCardTest {

    private Game game;
    private Player player;
    private PickCard pickCard;
    private PickCard pickCard1;

    @Before
    public void setUp() throws Exception {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.init();
        game.addPlayer("peppe");
        //game.start(new DefaulEventManagerFake());

        List<String> username = new LinkedList<>();
        username.add("peppe");
        GameList.getInstance().add(game,username);


        player = game.getGameStatus().getPlayers().get("peppe");
        Tower tower = game.getBoard().getTowers().get("YELLOW");
        Tower tower1 = game.getBoard().getTowers().get("BLUE");

       // EffectOn
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY,10);
        Effect effect = new EffectOnResources(resourceSet);

        ResourceSet resourceSet1 = new ResourceSet();
        resourceSet1.variateResource(Resource.MONEY,2);

        ResourceSet resourceSet2 = new ResourceSet();
        resourceSet2.variateResource(Resource.MONEY,2);


        List<ProdHarvEffect> prodHarvEffectList = new LinkedList<>();
        Map<Integer,List<ProdHarvEffect>> prodHarv = new HashMap<>();
        prodHarv.put(1,prodHarvEffectList);
        List<Effect> effects = new LinkedList<>();
        effects.add(effect);
        List<Requirement> requirements = new LinkedList<>();
        Requirement requirement = new Requirement(resourceSet1,resourceSet2);
        requirements.add(requirement);

      DevelopmentCard developmentCard = new DevelopmentCard("cartaPazza",1,requirements,effects,"YELLOW",prodHarv);
      game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(0).setCard(developmentCard);

        pickCard = new PickCard(player,tower,0,game);
       // pickCard = new PickCard(player,tower1,0,game);
    }


    @Test
    public void pickCard() throws InterruptedException {

       // assertTrue(pickCard.isAllowed());


        pickCard.execute();
        assertTrue(player.getPlayerBoard().getDevelopmentCards().size() == 1);
    }


    }


