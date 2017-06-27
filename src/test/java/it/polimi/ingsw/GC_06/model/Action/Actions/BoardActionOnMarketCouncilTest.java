package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAccess;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnResources;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
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





    @org.junit.Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaultEventManager(new ServerOrchestrator(), game));
        player = game.getGameStatus().getPlayers().get("peppe");
        action = new BoardActionOnMarketCouncil(game.getBoard().getMarket().get(0), 1, player.getFamilyMembers()[0], player, ActionType.BOARD_ACTION_ON_MARKET);
        action.setGame(game);

    }

    @Test
    public void firstTest() throws InterruptedException {
        int oldServantQuantity = player.getResourceSet().getResourceAmount(Resource.SERVANT);
        if (action.isAllowed())
            action.execute();
        assertTrue((game.getBoard().getMarket().get(0).getActionPlaces().get(1).getMembers().get(0).getPlayerUserName().equals("peppe")));
        assertTrue(oldServantQuantity + 5 == player.getResourceSet().getResourceAmount(Resource.SERVANT));
        // adesso vediamo se sono stati dati correttamente gli effetti

    }



    @Test
    public void resourceBonusTest() throws InterruptedException {

        int oldServantQuantity = player.getResourceSet().getResourceAmount(Resource.SERVANT);

        BonusMalusOnResources bonusMalusOnResources = new BonusMalusOnResources(Resource.MILITARYPOINT, -5, ActionType.RESOURCEACTION, false);
        BonusMalusOnResources bonusMalusOnResources1 = new BonusMalusOnResources(Resource.SERVANT, -2, ActionType.RESOURCEACTION, true);

        List<BonusMalusOnResources> bonusMalusOnResourcesList = new LinkedList<>();
        bonusMalusOnResourcesList.add(bonusMalusOnResources);
        bonusMalusOnResourcesList.add(bonusMalusOnResources1);

        player.getBonusMalusSet().addResourceBonusMalus(bonusMalusOnResourcesList);
        if (action.isAllowed())
            action.execute();

        assertTrue(oldServantQuantity + 3 == player.getResourceSet().getResourceAmount(Resource.SERVANT));
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