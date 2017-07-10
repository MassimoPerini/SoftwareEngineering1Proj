package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Effect.DonateBonusMalusEffect;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effects.DonateBonusMalusTest;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/28/17.
 */
public class HeroCardActionTest {

    private HeroCard heroCard;
    private Game game;
    private Player player;
    private HeroCardAction heroCardAction;
    private BoardActionOnTower boardActionOnTower;
    private FamilyMember familyMember;

    @Before
    public void setUp() throws IOException {

        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.init();
        game.addPlayer("gabri");
        game.addPlayer("peppe");
        HashMap<String, Integer> cardConditions = new HashMap<>();
        cardConditions.put("GREEN", -1);
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY, 3);

        String[] colours = {"WHITE"};
        List<String> colori = new LinkedList<>();
        colori.addAll(Arrays.asList(colours));
        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction("", colori, ActionType.GENERAL, true, 2);
        List<BonusMalusOnAction> bonusMalusOnActionList = new LinkedList<>();
        bonusMalusOnActionList.add(bonusMalusOnAction);
        BonusMalusSet bonusMalusSet = new BonusMalusSet();
        bonusMalusSet.addActionBonusMalus(bonusMalusOnActionList);

        DonateBonusMalusEffect donateBonusMalusEffect = new DonateBonusMalusEffect(bonusMalusSet);
        List<Effect> effects = new LinkedList<>();
        effects.add(donateBonusMalusEffect);

        heroCard = new HeroCard("", cardConditions, resourceSet, effects, true, "permanent");

        FamilyMember familyMember = new FamilyMember("WHITE", "peppe");
        familyMember.setValue(100);

        FamilyMember[] familyMembers = {familyMember};

        player = new Player("peppe", familyMembers);
        player.getResourceSet().variateResource(Resource.MONEY, 10);
        player.getHeroCard().add(heroCard);
        game = new Game(0);
        List<Integer> integers = new LinkedList<>();
        integers.add(0);
        heroCardAction = new HeroCardAction(game,player,integers,ActionType.PLAY_HERO_CARD);

    }


    @Test

    public void FirstTest() throws InterruptedException {


        assertTrue((heroCard.isActivable(player)));
        heroCardAction.execute();
        assertTrue(player.getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size() == 1);


    }
}
