package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 6/25/17.
 */
public class BoardActionOnTowerTest {

    private Tower tower;
    private int index;
    private FamilyMember familyMember;
    private Game game;
    private Player player;
    private BoardActionOnTower action;

    @Before
    public void setUp()throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaultEventManager(new ServerOrchestrator(),game));
        tower = game.getBoard().getTowers().get("YELLOW");
        index = 2;
        familyMember = game.getGameStatus().getPlayers().get("peppe").getFamilyMembers()[0];
        familyMember.setValue(100);

        action = new BoardActionOnTower(game.getGameStatus().getPlayers().get("peppe"),index,tower,familyMember,game);

    }

    @Test
    public void bonusMalusTest(){

        LinkedList<String> colours = new LinkedList<>();
        colours.add(familyMember.getDiceColor());
        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction("YELLOW",colours, ActionType.BOARD_ACTION_ON_TOWER,true,-3);
        List<BonusMalusOnAction> bonusMalusOnActions = new LinkedList<>();
        bonusMalusOnActions.add(bonusMalusOnAction);
        game.getGameStatus().getPlayers().get("peppe").getBonusMalusSet().addActionBonusMalus(bonusMalusOnActions);

        assertTrue(1== game.getGameStatus().getPlayers().get("peppe").getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size());

        action.run();

        assertTrue(97 == familyMember.getValue());

        assertTrue(1 == game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(2).getActionPlace().getMembers().size());
        assertTrue((game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(2).getActionPlace().getMembers().get(0).getPlayerUserName()).equals("peppe"));

        assertTrue((game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(2).getCard()) == null);
        assertTrue(1 == game.getGameStatus().getPlayers().get("peppe").getPlayerBoard().getDevelopmentCards("YELLOW").size());

    }










}