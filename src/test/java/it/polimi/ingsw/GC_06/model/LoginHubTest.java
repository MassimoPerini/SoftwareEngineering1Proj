package it.polimi.ingsw.GC_06.model;

import it.polimi.ingsw.GC_06.Server.Network.LoginHub;
import org.junit.Before;

import java.io.IOException;

/**
 * Created by giuseppe on 6/16/17.
 */
public class LoginHubTest {

    private LoginHub loginHub;

    @Before
    public void setUp() throws IOException {
        LoginHub.getInstance().addUser("Peppe");
        LoginHub.getInstance().addUser("Massi");
        LoginHub.getInstance().addUser("Gabri");
        LoginHub.getInstance().addUser("boh");
        LoginHub.getInstance().addUser("cdsdfv");
        //loginHub.addUser("Peppe");

    }

/*    @Test
    public void firstTest(){

        assertTrue(5 == loginHub.getTotPlayers().size());
        assertTrue(1 == loginHub.getLoggedPlayers().size());
        assertFalse(loginHub.access("Peppe"));
    }
    */
}
