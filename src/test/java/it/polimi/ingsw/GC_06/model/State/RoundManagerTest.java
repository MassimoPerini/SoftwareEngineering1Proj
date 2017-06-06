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

    private RoundManager roundManager;

    @Before
    public void setUp() throws Exception {
        this.roundManager = new RoundManager(FileLoader.getFileLoader().loadBoard(), FileLoader.getFileLoader().loadDiceSet().getDices().length + Integer.parseInt(Setting.getInstance().getProperty("neutral_family_members")));
    }

    @Test
    public void testInitValues()
    {
        assertTrue(roundManager.getEra() == 1);
        assertTrue(roundManager.getTurn() == 1);
        assertTrue(roundManager.getFamilyMembersPlaced() == 0);
    }

    @Test (expected=IllegalStateException.class)
    public void testNoPlayers()
    {
        testValues(1);
    }

    @Test
    public void testOnePlayer()
    {
        generateFamilyMembers(1);
        testValues(1);
    }

    @Test
    public void testMorePlayers()
    {
        generateFamilyMembers(3);
        testValues(3);
    }

    public void generateFamilyMembers(int n)
    {
        for (int i=0;i<n;i++){
            FamilyMember [] familyMembers = new FamilyMember[2];
            familyMembers[0] = new FamilyMember("YELLOW", "massimo");
            familyMembers[1] = new FamilyMember("GREEN", "massimo");
            Player p1 = new Player("massimo", familyMembers);
            roundManager.addPlayer(p1);
        }
    }

    private void testValues(int nPlayers)
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

        testInitValues();
    }

    @Test
    public void testStart() throws IOException {
        ResourceSet [] resourceSets = FileLoader.getFileLoader().loadDefaultResourceSets();
        generateFamilyMembers(3);
        roundManager.start();
        assertTrue(roundManager.getCurrentPlayer().getResourceSet().isIncluded(resourceSets[0]));
        assertTrue(resourceSets[0].isIncluded(roundManager.getCurrentPlayer().getResourceSet()));

    }


}