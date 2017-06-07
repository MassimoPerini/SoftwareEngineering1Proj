package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by massimo on 06/06/17.
 */
public class RoundManagerTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();

    }

    @Test
    public void testInitValues()
    {
        game.addPlayer("Massimo");
        game.start();
        checkInitValues();
    }


    private void checkInitValues()
    {
        RoundManager roundManager = game.getRoundManager();

        assertTrue(roundManager.getEra() == 1);
        assertTrue(roundManager.getTurn() == 1);
        assertTrue(roundManager.getFamilyMembersPlaced() == 0);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testNoPlayers()
    {
        game.start();
        RoundManager roundManager = game.getRoundManager();

        testValues(1, roundManager);
    }

    @Test
    public void testOnePlayer()
    {
        game.addPlayer("Massimo");
        game.start();
        RoundManager roundManager = game.getRoundManager();
        testValues(1, roundManager);
    }

    @Test
    public void testMorePlayers()
    {
        game.addPlayer("Massimo");
        game.addPlayer("Perini");
        game.addPlayer("ciao");
        game.start();
        RoundManager roundManager = game.getRoundManager();

        testValues(3, roundManager);
    }


    private void testValues(int nPlayers, RoundManager roundManager)
    {
        for (int i=1;i<=roundManager.getMaxEras();i++)
        {
            assertTrue(roundManager.getEra() == i);
            for (int j=1;j<=roundManager.getMaxTurns();j++)
            {
                assertTrue(roundManager.getTurn() == j);
                for (int k=0;k<roundManager.getnMaxFamilyMembers();k++)
                {
                    assertTrue(roundManager.getFamilyMembersPlaced() == k);
                    for (int a=0;a<nPlayers;a++){
                        roundManager.endTurn();
                    }
                }
                assertTrue(roundManager.getFamilyMembersPlaced() == 0);
            }
            assertTrue(roundManager.getFamilyMembersPlaced() == 0);
            assertTrue(roundManager.getTurn() == 1);
        }

        checkInitValues();
    }

    @Test
    public void testStart() throws IOException {
        ResourceSet [] resourceSets = FileLoader.getFileLoader().loadDefaultResourceSets();
        game.addPlayer("Massimo");
        game.addPlayer("Perini");
        game.addPlayer("ciao");
        game.start();
        RoundManager roundManager = game.getRoundManager();

        assertTrue(roundManager.getCurrentPlayer().getResourceSet().isIncluded(resourceSets[0]));
        assertTrue(resourceSets[0].isIncluded(roundManager.getCurrentPlayer().getResourceSet()));

    }

}