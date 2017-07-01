package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAccess;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnResources;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


/**
 * Created by giuseppe on 6/26/17.
 */
public class BoardActionOnMarketCouncilTest {


    private Game game;
    private ActionType actionType;
    private Player player;
    private BoardActionOnMarketCouncil action;
    private FamilyMember familyMember;





    @org.junit.Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");

        game.start(new DefaulEventManagerFake());
        player = game.getGameStatus().getPlayers().get("peppe");
        familyMember = player.getFamilyMembers()[1];
        familyMember.setValue(100);
        action = new BoardActionOnMarketCouncil(game.getBoard().getMarket().get(0), 0, player.getFamilyMembers()[1], player, ActionType.BOARD_ACTION_ON_MARKET,game);
        action.setGame(game);

    }

    @Test
    public void firstTest() throws InterruptedException {
        int oldMoneyQuantity = player.getResourceSet().getResourceAmount(Resource.MONEY);
        if (action.isAllowed())
            action.execute();
        assertTrue((game.getBoard().getMarket().get(0).getActionPlaces().get(0).getMembers().get(0).getPlayerUserName().equals("peppe")));
        assertTrue(oldMoneyQuantity + 5 == player.getResourceSet().getResourceAmount(Resource.MONEY));
        // adesso vediamo se sono stati dati correttamente gli effetti

    }



    @Test
    public void resourceBonusTest() throws InterruptedException {

        int oldMoneyQuantity = player.getResourceSet().getResourceAmount(Resource.MONEY);

        BonusMalusOnResources bonusMalusOnResources = new BonusMalusOnResources(Resource.MILITARYPOINT, -5, ActionType.RESOURCEACTION, false);
        BonusMalusOnResources bonusMalusOnResources1 = new BonusMalusOnResources(Resource.MONEY, -2, ActionType.RESOURCEACTION, true);

        List<BonusMalusOnResources> bonusMalusOnResourcesList = new LinkedList<>();
        bonusMalusOnResourcesList.add(bonusMalusOnResources);
        bonusMalusOnResourcesList.add(bonusMalusOnResources1);

        player.getBonusMalusSet().addResourceBonusMalus(bonusMalusOnResourcesList);
        if (action.isAllowed())
            action.execute();

        assertTrue(oldMoneyQuantity + 3 == player.getResourceSet().getResourceAmount(Resource.MONEY));
        assertTrue(player.getBonusMalusSet().getBonusMalusOnResources().get(BonusMalusType.BONUSMALUSONRESOURCE).size() == 1);

    }

    @Test
    public void accesMalusTest() throws InterruptedException {

        BonusMalusOnAccess bonusMalusOnAccess = new BonusMalusOnAccess(ActionType.BOARD_ACTION_ON_MARKET, false, true);
        List<BonusMalusOnAccess> bonusMalusOnAccesses = new LinkedList<>();
        bonusMalusOnAccesses.add(bonusMalusOnAccess);

        player.getBonusMalusSet().addAccessBonusMalus(bonusMalusOnAccesses);
        assertFalse(action.isAllowed());


    }
}