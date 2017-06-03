package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Dice.DiceColor;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
    @Before
    public void setUp() throws Exception {
        Game.clearForTesting();
        Game.getInstance().addPlayer("massimo");
        player = Game.getInstance().getGameStatus().getCurrentPlayer();
        familyMember = player.getFamilyMembers()[0];
        Game.getInstance().roll();
    }

    @Test (expected=NullPointerException.class)
    public void nullEffects() throws Exception {
        actionPlace = new ActionPlace(null, 5);
    }

    @Test
    public void checkValueFamilyMember(){
        int val = player.getFamilyMembers()[0].getValue();
        assertTrue(val < 7 && val > 0);
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
        Game.getInstance().roll();
        actionPlace.addFamilyMember(player.getFamilyMembers()[0]);
    }


}