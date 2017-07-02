package it.polimi.ingsw.GC_06.Server.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.Client.PlayerChoiceExcommunication;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by gabri on 02/07/2017.
 */
public class PlayerChoiceExcomunicationTest {
    Player player;
    Game game;
    private boolean activateExcommunication;

    @Before
    public void SetUp() throws IOException {
        game = new Game(0);
        game.addPlayer("gabri");
        game.start(new DefaulEventManagerFake());
        player = game.getGameStatus().getPlayers().get("gabri");
    }

    @Test
    public void FirstTest() throws IOException {
        activateExcommunication = true;
        PlayerChoiceExcommunication p = new PlayerChoiceExcommunication(activateExcommunication);
        p.setGame(0);
        p.setPlayer("gabri");

        //p.execute();
        //TODO peppe qui come ti dicevo ci sono gli errori sulla GameList
    }
}
