package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Effect.DonateBonusMalusEffect;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effects.DonateBonusMalusTest;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());


       player = game.getGameStatus().getPlayers().get("peppe");

        /*** qui mi sono creato una hero card che darò al giocatore */
        HashMap<String ,Integer> cardRequirements = new HashMap<>();
        cardRequirements.put("GREEN",1);
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY,30);
        LinkedList<String> colours = new LinkedList<>();
        String[] colour = {"RED","WHITE","BLACK",""};
        colours.addAll(Arrays.asList(colour));



        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction(null,colours,ActionType.GENERAL,true,2);
        List<BonusMalusOnAction> bonusMalusOnActions = new LinkedList<>();
        bonusMalusOnActions.add(bonusMalusOnAction);

        BonusMalusSet bonusMalusSet = new BonusMalusSet();
        bonusMalusSet.addActionBonusMalus(bonusMalusOnActions);

        Effect donateBonusMalus = new DonateBonusMalusEffect(bonusMalusSet);
        List<Effect> effects = new LinkedList<>();
        effects.add(donateBonusMalus);
        heroCard = new HeroCard("",cardRequirements,resourceSet,effects,true,"permanent");

        //player = game.getGameStatus().getPlayers().get("peppe");
        List<Integer> choices = new LinkedList<>();
        choices.add(0);

        FamilyMember[] familyMembers = {};


        player.getHeroCard().add(heroCard);

        heroCardAction = new HeroCardAction(game,player,choices,ActionType.PLAY_HERO_CARD);
        familyMember =player.getFamilyMembers()[0];
        familyMember.setValue(100);
        boardActionOnTower = new BoardActionOnTower(player,0,game.getBoard().getTowers().get("PURPLE"),familyMember,game);

    }


    @Test

    public void FirstTest() throws InterruptedException {


        assertTrue(heroCard.isActivable(player));
        heroCardAction.execute();
        assertTrue(player.getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size() == 1);
        boardActionOnTower.execute();
      assertTrue(familyMember.getValue() == 102);
    }
}
