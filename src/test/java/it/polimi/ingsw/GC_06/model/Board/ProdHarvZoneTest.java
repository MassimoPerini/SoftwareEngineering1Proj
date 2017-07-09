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
public class ProdHarvZoneTest {
    private Game game;
    private Board board;
    private List<ProdHarvZone> prodHarvZones;

    @Before
    public void SetUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.init();
        game.addPlayer("gabri");
        game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[0].setValue(100);
        game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[1].setValue(100);
        board = game.getBoard();
      //  prodHarvZones = board.getProdHarvZones();
    }

    @Test (expected = NullPointerException.class)
    public void addTest() throws IOException {
        ProdHarvZone prodHarvZone = prodHarvZones.get(0);
        FamilyMember familyMember = game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[0];
        prodHarvZone.addFamilyMember(familyMember, 0);
        assertTrue(prodHarvZone.getActionPlaces().get(0).getMembers().size()==1);
    }

    @Test (expected = NullPointerException.class)
    public void multipleAddTest() throws IOException {
        ProdHarvZone prodHarvZone = prodHarvZones.get(0);
        FamilyMember familyMember = game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[0];
        FamilyMember familyMember2 = game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[1];
        prodHarvZone.addFamilyMember(familyMember, 0);
        prodHarvZone.addFamilyMember(familyMember2, 0);
        prodHarvZone.addFamilyMember(familyMember, 1);
        prodHarvZone.addFamilyMember(familyMember2, 1);
        assertTrue(prodHarvZone.getActionPlaces().get(0).getMembers().size()==2);
        assertTrue(prodHarvZone.getActionPlaces().get(1).getMembers().size()==2);
    }

    @Test (expected = NullPointerException.class)
    public void removeTest() throws IOException {
        ProdHarvZone prodHarvZone = prodHarvZones.get(0);
        FamilyMember familyMember = game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[0];
        FamilyMember familyMember2 = game.getGameStatus().getPlayers().get("gabri").getFamilyMembers()[1];
        prodHarvZone.addFamilyMember(familyMember, 0);
        prodHarvZone.addFamilyMember(familyMember2, 0);
        prodHarvZone.addFamilyMember(familyMember, 1);
        prodHarvZone.addFamilyMember(familyMember2, 1);
        prodHarvZone.removeFamilyMembers();
        assertTrue(prodHarvZone.getActionPlaces().get(0).getMembers().size()==0);
        assertTrue(prodHarvZone.getActionPlaces().get(1).getMembers().size()==0);
    }


}
