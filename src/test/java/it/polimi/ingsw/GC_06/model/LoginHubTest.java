package it.polimi.ingsw.GC_06.model;

import it.polimi.ingsw.GC_06.Server.Network.LoginHub;
import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/16/17.
 */
public class LoginHubTest {

    private LoginHub loginHub = new LoginHub(new ServerOrchestrator());

    @Before
    public void setUp() throws IOException {
        loginHub.addUser("Peppe");
        loginHub.addUser("Massi");
        loginHub.addUser("Gabri");
        loginHub.addUser("boh");
        loginHub.addUser("cdsdfv");
        //loginHub.addUser("Peppe");

    }

    @Test
    public void firstTest(){

        assertTrue(5 == loginHub.getTotPlayers().size());
        assertTrue(1 == loginHub.getLoggedPlayers().size());
        assertFalse(loginHub.access("Peppe"));

    }
}
