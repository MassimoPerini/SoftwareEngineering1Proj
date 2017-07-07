package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.model.Dice.Dice;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 03/07/2017.
 */
public class FamilyMemberTest {
    private FamilyMember[] familyMembers;
    private Game game;
    private Player player;
    private String colour;
    private int diceValue;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(0);
        game.addPlayer("gabri");
        player = game.getGameStatus().getPlayers().get("gabri");
        familyMembers = player.getFamilyMembers();
        colour = familyMembers[0].getDiceColor();
    }

    @Test
    public void firstTest() throws IOException {
        //System.out.print(colour);
        DiceSet diceSet = game.getDiceSet();
        Dice[] dices = diceSet.getDices();
        colour = dices[0].getColor();
        //System.out.print(colour);
        assertTrue(familyMembers[0].getValue()==dices[0].getPoints());
    }
}
