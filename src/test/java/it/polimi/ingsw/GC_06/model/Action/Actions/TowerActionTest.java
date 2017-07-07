package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/27/17.
 */
public class TowerActionTest {


    private Tower tower;
    private int index;
    private FamilyMember familyMember;
    private FamilyMember familyMember1;
    private Game game;
    private Player player;
    private BoardActionOnTower action;
    private BoardActionOnTower action1;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());
        tower = game.getBoard().getTowers().get("YELLOW");
        index = 2;
        familyMember = game.getGameStatus().getPlayers().get("peppe").getFamilyMembers()[0];
        familyMember.setValue(100);
        familyMember1 = game.getGameStatus().getPlayers().get("peppe").getFamilyMembers()[3];
        familyMember1.setValue(100);

        player = game.getGameStatus().getPlayers().get("peppe");

        action = new BoardActionOnTower(player, index, tower, familyMember, game);
        action1 = new BoardActionOnTower(player, 0, tower, familyMember1, game);


    }

    @org.junit.Test
    public void bonusMalusTest() throws InterruptedException {


        // creo il bonus e malus e lo assegno al giocatore
        LinkedList<String> colours = new LinkedList<>();
        colours.add(familyMember.getDiceColor());
        BonusMalusOnAction bonusMalusOnAction = new BonusMalusOnAction("YELLOW", colours, ActionType.BOARD_ACTION_ON_TOWER, true, -3);
        BonusMalusOnAction bonusMalusOnAction1 = new BonusMalusOnAction("YELLOW", colours, ActionType.BOARD_ACTION_ON_TOWER, false, -1);
        BonusMalusOnAction bonusMalusOnAction2 = new BonusMalusOnAction("BLUE", colours, ActionType.BOARD_ACTION_ON_TOWER, false, -3);


        List<BonusMalusOnAction> bonusMalusOnActions = new LinkedList<>();
        bonusMalusOnActions.add(bonusMalusOnAction);
        bonusMalusOnActions.add(bonusMalusOnAction1);
        bonusMalusOnActions.add(bonusMalusOnAction2);
        game.getGameStatus().getPlayers().get("peppe").getBonusMalusSet().addActionBonusMalus(bonusMalusOnActions);

        assertTrue(3 == game.getGameStatus().getPlayers().get("peppe").getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size());

            assertTrue(action.isAllowed());
            assertTrue(100 == familyMember.getValue());


            action.execute();


        assertTrue(96 == familyMember.getValue());

        assertTrue(1 == game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(2).getActionPlace().getMembers().size());
        assertTrue((game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(2).getActionPlace().getMembers().get(0).getPlayerUserName()).equals("peppe"));

        assertTrue((game.getBoard().getTowers().get("YELLOW").getTowerFloor().get(2).getCard()) == null);
        assertTrue(1 == game.getGameStatus().getPlayers().get("peppe").getPlayerBoard().getDevelopmentCards("YELLOW").size());

        assertTrue(2 == player.getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size());

        }

/*
    @Test
    public void doubleAction() throws InterruptedException {

        action.execute();
        game.endTurn();
        action1.execute();

        assertTrue(97 == player.getResourceSet().getResourceAmount(Resource.MONEY));

        }

*/

    }


