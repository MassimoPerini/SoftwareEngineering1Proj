package it.polimi.ingsw.GC_06.model.Dice;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 03/07/2017.
 */
public class DiceTest {
    private Dice dice;
    private Dice dice2;

    @Before
    public void setUp() throws IOException {
        dice = new Dice(1, 6, "RED");
        dice2 = new Dice(5, 10, "gabriPower");
    }

    @Test
    public void rollTest() throws IOException {
        dice.roll();
        dice2.roll();
        assertTrue(dice.getPoints()>=1);
        assertTrue(dice.getPoints()<=6);
        assertTrue(dice2.getPoints()<=10);
        assertTrue(dice2.getPoints()>=5);
    }
}
