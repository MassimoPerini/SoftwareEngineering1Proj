package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by massimo on 28/05/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ActionPlaceTest {

    private ActionPlace actionPlace;
    private FamilyMember familyMember;
    private Player player;
    private Game game;
    @Before
    public void setUp() throws Exception {
    //    Game.clearForTesting();

        game = new Game();

        game.addPlayer("massimo");
        player = game.getGameStatus().getPlayers().get("massimo");
        familyMember = player.getFamilyMembers()[0];
        game.roll();
    }

    @Test (expected=NullPointerException.class)
    public void nullEffects() throws Exception {
        actionPlace = new ActionPlace(null, 5);
    }

    @Test
    public void checkValueFamilyMember() throws IOException {
        int val = player.getFamilyMembers()[0].getValue();
        assertTrue(val <= (FileLoader.getFileLoader().loadDiceSet().getDices()[0].getMaxPoint()));
        assertTrue(val >= (FileLoader.getFileLoader().loadDiceSet().getDices()[0].getMinPoint()));
        assertEquals(player.getFamilyMembers()[0].getDiceColor(), (FileLoader.getFileLoader().loadDiceSet().getDices()[0].getColor()));

    }

    @Test
    public void checkForbiddenActionPlace(){
        actionPlace = new ActionPlace(new LinkedList<>(), 20000);
        assertFalse(actionPlace.isAllowed(player.getFamilyMembers()[0]));
    }

    @Test
    public void checkAllowedActionPlace(){
        actionPlace = new ActionPlace(new LinkedList<>(), -1);
        for (int i=0;i<150;i++) {
            assertTrue(actionPlace.isAllowed(player.getFamilyMembers()[0]));
            actionPlace.addFamilyMember(player.getFamilyMembers()[0]);
        }
    }

    @Test
    public void checkAddedFamilyMember(){
        actionPlace = new ActionPlace(new LinkedList<>(), -1);
        actionPlace.addFamilyMember(familyMember);
        assertEquals(actionPlace.getMembers().get(0).getPlayerUserName(), player.getPLAYER_ID());
        assertTrue(actionPlace.getMembers().size() == 1);
    }

    @Test
    public void checkEffects(){
        List<Effect> effectList = new LinkedList<>();
        Effect effect = new EffectOnResources(new ResourceSet());
        effectList.add(effect);
        actionPlace = new ActionPlace(effectList, -1);
        assertTrue(actionPlace.getEffects().size()==1);
        assertEquals(actionPlace.getEffects().get(0), effect);

    }

    @Test (expected=NullPointerException.class)
    public void checkNullFamilyMemberActionPlace(){
        actionPlace = new ActionPlace(new LinkedList<>(), -1);
        actionPlace.addFamilyMember(null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void notAllowedAddActionPlace(){
        actionPlace = new ActionPlace(new LinkedList<>(), 20000);
        game.roll();
        actionPlace.addFamilyMember(player.getFamilyMembers()[0]);
    }


}