package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 02/07/2017.
 */
public class MarketAndCoucilTest {
    private List<MarketAndCouncil> marketAndCouncils;
    private Game game;
    private Board board;

    @Before
    public void SetUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.addPlayer("gabri");
        game.addPlayer("peppe");
        game.addPlayer("massi");

        board = game.getBoard();
        marketAndCouncils = board.getCouncils();
        game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[0].setValue(100);
    }

    @Test
    public void addTest() throws IOException {
        MarketAndCouncil marketAndCouncil = marketAndCouncils.get(0);
        FamilyMember familyMember = game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[0];
        marketAndCouncil.addFamilyMember(familyMember, 0);
        assertTrue(marketAndCouncil.getActionPlaces().get(0).getMembers().size()==1);
    }

    @Test
    public void removeTest() throws IOException {
        MarketAndCouncil marketAndCouncil = marketAndCouncils.get(0);
        FamilyMember familyMember = game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[0];
        marketAndCouncil.addFamilyMember(familyMember, 0);
        assertTrue(marketAndCouncil.getActionPlaces().get(0).getMembers().size()==1);
        marketAndCouncil.removeFamilyMembers();
        assertTrue(marketAndCouncil.getActionPlaces().get(0).getMembers().size()==0);
    }

    @Test
    public void getEffectsTest() throws IOException {
        MarketAndCouncil marketAndCouncil = marketAndCouncils.get(0);
        assertTrue(marketAndCouncil.getActionPlaces().get(0).getEffects().size()==2);
    }
}
