package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/2/17.
 */
public class LoginHubTest {

    private Game game;

    @Before
    public void setUp() throws IOException {

        LoginHub.getInstance().setServerOrchestrator(new ServerOrchestrator());
    }

    /** questo è da sistemare*/
    @Test
    public void firstTest() throws IOException {



        Game game = new Game(0);
        game.addPlayer("peppe");
        game.addPlayer("massimo");
        LinkedList<String> gamers = new LinkedList();
        Set<String> players = game.getGameStatus().getPlayers().keySet();

        for (String player : players) {
            gamers.add(player);
        }

        LoginHub.getInstance().access("peppe");
        LoginHub.getInstance().access("massimo");
        assertTrue(LoginHub.getInstance().getTotPlayers().size() == 2);
        GameList.getInstance().add(game,gamers);
        LoginHub.getInstance().manageLogOut("peppe");
        assertTrue(LoginHub.getInstance().getTotPlayers().size() == 1);
      //  assertTrue(GameList.getInstance().getGameMap().get(game).size()==1);
        assertTrue(LoginHub.getInstance().searchTrash("peppe"));

        LoginHub.getInstance().restoreInTheGame("peppe",0);
     //   assertTrue(GameList.getInstance().getGameMap().get(game).size()==2);
        assertTrue(game.getGameStatus().getPlayers().get("peppe").isConnected());



    }


}