package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by massimo on 06/06/17.
 */
public class GameStatusTest {

    private GameStatus gameStatus;

    @Before
    public void setUp() throws Exception {
        gameStatus = new GameStatus(new State(StateName.IDLE));
    }

    @Test (expected = IllegalArgumentException.class)
    public void startNoPlayers() {
        gameStatus.start();
    }

    @Test (expected = IllegalArgumentException.class)
    public void startNoEnaughtPlayers()
    {
        for (int i=0;i<gameStatus.getMinPlayers()-1;i++){
            FamilyMember[] familyMembers = new FamilyMember[1];
            familyMembers[0] = new FamilyMember("YELLOW", "massimo");
            Player player = new Player("massimo", familyMembers);
            gameStatus.addPlayer(player);
        }
        gameStatus.start();
    }

    @Test
    public void canStart()
    {
        for (int i=0;i<gameStatus.getMinPlayers();i++){
            FamilyMember[] familyMembers = new FamilyMember[1];
            familyMembers[0] = new FamilyMember("YELLOW", "massimo");
            Player player = new Player("massimo"+i, familyMembers);
            gameStatus.addPlayer(player);
        }
        gameStatus.start();
        assertTrue(gameStatus.getPlayers().size() == gameStatus.getMinPlayers());
    }

    @Test (expected = IllegalArgumentException.class)
    public void sameName()
    {
        for (int i=0;i<gameStatus.getMinPlayers();i++){
            FamilyMember[] familyMembers = new FamilyMember[1];
            familyMembers[0] = new FamilyMember("YELLOW", "massimo");
            Player player = new Player("massimo", familyMembers);
            gameStatus.addPlayer(player);
        }
        gameStatus.start();
        assertTrue(gameStatus.getPlayers().size() == gameStatus.getMinPlayers());
    }

}