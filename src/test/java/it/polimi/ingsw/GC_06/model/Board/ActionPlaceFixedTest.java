package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by massimo on 29/05/17.
 */
public class ActionPlaceFixedTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        Game.clearForTesting();
        Game.getInstance().addPlayer("massimo");
        player = Game.getInstance().getGameStatus().getCurrentPlayer();
        Game.getInstance().roll();
    }

    @Test (expected=NullPointerException.class)
    public void checkNull() {
        ActionPlace actionPlace = new ActionPlaceFixed(null, 0, 0);
    }

    @Test
    public void checkLimitAllowed() {
        ActionPlace actionPlace = new ActionPlaceFixed(new ArrayList<>(), -1, 1);
        FamilyMember familyMember = player.getFamilyMembers()[0];
        assertTrue(actionPlace.isAllowed(familyMember));
    }

    @Test
    public void checkLimitNotAllowed() {
        ActionPlace actionPlace = new ActionPlaceFixed(new ArrayList<>(), -1, 1);
        FamilyMember familyMember = player.getFamilyMembers()[0];
        actionPlace.addFamilyMember(familyMember);
        assertFalse(actionPlace.isAllowed(familyMember));
    }

    @Test
    public void allowed() {
        ActionPlace actionPlace = new ActionPlaceFixed(new ArrayList<>(), -1, 2);
        FamilyMember familyMember = player.getFamilyMembers()[0];
        actionPlace.addFamilyMember(familyMember);
        actionPlace.addFamilyMember(familyMember);
    }

    @Test (expected=IllegalArgumentException.class)
    public void notAllowedWithZero() {
        ActionPlace actionPlace = new ActionPlaceFixed(new ArrayList<>(), -1, 0);
        FamilyMember familyMember = player.getFamilyMembers()[0];
        actionPlace.addFamilyMember(familyMember);
    }

    @Test (expected=IllegalArgumentException.class)
    public void notAllowed() {
        ActionPlace actionPlace = new ActionPlaceFixed(new ArrayList<>(), -1, 3);
        FamilyMember familyMember = player.getFamilyMembers()[0];
        actionPlace.addFamilyMember(familyMember);
        actionPlace.addFamilyMember(familyMember);
        actionPlace.addFamilyMember(familyMember);
        actionPlace.addFamilyMember(familyMember);

    }


    @Test (expected=IllegalArgumentException.class)
    public void priceTooHigh() {
        ActionPlace actionPlace = new ActionPlaceFixed(new ArrayList<>(), 9000, 2);
        FamilyMember familyMember = player.getFamilyMembers()[0];
        actionPlace.addFamilyMember(familyMember);
    }


}