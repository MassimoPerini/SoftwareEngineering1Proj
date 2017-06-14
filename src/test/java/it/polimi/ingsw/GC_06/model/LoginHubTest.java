package it.polimi.ingsw.GC_06.model;


import it.polimi.ingsw.GC_06.Network.LoginHub;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/14/17.
 */
public class LoginHubTest {
    String username = "peppe";
    String username1 = "massi";
    String username2 = "gabri";
    String username3 = "boh";

    @Before
    public void setUp() throws IOException {
        LoginHub.getInstance();
        LoginHub.getInstance().addUser(username);
        LoginHub.getInstance().addUser(username1);
        LoginHub.getInstance().addUser(username2);
        LoginHub.getInstance().addUser(username3);

    }



    @Test
    public void firstTest() {

        assertTrue(0 ==LoginHub.getInstance().getLoggedPlayers().size());
        assertTrue(4 ==LoginHub.getInstance().getTotPlayers().size());

    }

    @Test
    public void secondTest() throws IOException {
        assertFalse(LoginHub.getInstance().access("peppe"));
       assertTrue(0 ==LoginHub.getInstance().getLoggedPlayers().size());
        assertTrue(4 ==LoginHub.getInstance().getTotPlayers().size());
    }

}
