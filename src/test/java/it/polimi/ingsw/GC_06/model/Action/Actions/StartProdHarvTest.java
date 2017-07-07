package it.polimi.ingsw.GC_06.model.Action.Actions;

/**
 * Created by giuseppe on 6/28/17.
 */

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Action.PickCard.PickCard;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.AskUserCard;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.DefaultAskUserSelector;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.StartProdHarv;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.*;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by giuseppe on 6/24/17.
 */
public class StartProdHarvTest {

    private ActionType actionType;
    private AskUserCard askUserCard;
    private int value;
    private Game game;
    private Player player;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        BonusMalusSet bonusMalusSet = new BonusMalusSet();

        List<String> colours = new LinkedList<>();
        String[] colour = {"ORANGE","WHITE","BLACK"," "};
        colours = Arrays.asList(colour);
        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction(null,colours,ActionType.PRODUCTION_ACTION,true,2);
        List<BonusMalusOnAction> bonusMalusOnActions = new LinkedList<>();
        bonusMalusOnActions.add(bonusMalusOnAction);

        bonusMalusSet.addActionBonusMalus(bonusMalusOnActions);

        game = new Game(0);

        game.addPlayer("peppe");
        player = game.getGameStatus().getPlayers().get("peppe");
        askUserCard = new DefaultAskUserSelector();
        value = 2;
        List<Effect> effects = new LinkedList<>();
        Effect donateBonusMalus = new DonateBonusMalusEffect(bonusMalusSet);
        effects.add(donateBonusMalus);
        DevelopmentCard developmentCard = new DevelopmentCard("",1,new LinkedList<>(),effects,"BLUE",new HashMap<>());
        game.start(new DefaulEventManagerFake());
        game.getBoard().getTowers().get("BLUE").getTowerFloor().get(0).setCard(developmentCard);

    }

    @Test
    public void bonusMalusTest() throws InterruptedException {

        int military =  player.getResourceSet().getResourceAmount(Resource.MILITARYPOINT);
        HashMap<Integer,List<ProdHarvEffect>> map = new HashMap<>();

        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MILITARYPOINT,10);

        Effect effectOnResources = new EffectOnResources(resourceSet);

        List<Effect> effects = new LinkedList<>();
        effects.add(effectOnResources);

        ProdHarvEffect prodHarvEffect = new ProdHarvEffect(new LinkedList<ProdHarvMalusEffect>(),effects);
        List<ProdHarvEffect> prodHarvEffectList = new LinkedList<>();
        prodHarvEffectList.add(prodHarvEffect);

        map.put(6,prodHarvEffectList);

        DevelopmentCard developmentCard = new DevelopmentCard("",1,new LinkedList<>(),new LinkedList<>(),"YELLOW",map);

        game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(0).setCard(developmentCard);

        Action pickCard0 = new PickCard(player,game.getBoard().getTowers().get("YELLOW"),0,game);
        if(pickCard0.isAllowed())
            pickCard0.execute();
        StartProdHarv startProdHarv = new StartProdHarv(ActionType.PRODUCTION_ACTION, new DefaultAskUserSelector(),5,player,game);
        startProdHarv.execute();


        assertTrue(player.getResourceSet().getResourceAmount(Resource.MILITARYPOINT) == military);

        Action pickCard = new PickCard(player,game.getBoard().getTowers().get("BLUE"),0,game);

        pickCard.execute();

        startProdHarv.execute();
      assertTrue(7 == startProdHarv.getValue());
      assertTrue(player.getResourceSet().getResourceAmount(Resource.MILITARYPOINT) == military +10);

    }
}
