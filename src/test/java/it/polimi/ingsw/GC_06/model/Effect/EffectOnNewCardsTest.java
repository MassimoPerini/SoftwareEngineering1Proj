package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Network.*;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.State.DefaultEventManager;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/2/17.
 */
public class EffectOnNewCardsTest {

    private Game game;
    private Map<String,Integer> towerFloors;
    private EffectOnNewCards effectOnNewCards;
    private Player player;
    private FakeEventManager fakeEventManager;



    @Before
    public void setUp() throws IOException {


        game = new Game(0);
        game.addPlayer("peppe");
        ServerOrchestrator serverOrchestrator = new ServerOrchestrator();
        game.start(new FakeEventManager(game,serverOrchestrator));

        player = game.getGameStatus().getPlayers().get("peppe");



        towerFloors = new HashMap<>();
        towerFloors.put("YELLOW",2);
        // dobbiamo fare in modo che il player abbia un server
        serverOrchestrator.addServer(new SocketServer());
        serverOrchestrator.startGame(game.getGameStatus().getPlayers(),0);
        effectOnNewCards = new EffectOnNewCards(towerFloors);
        effectOnNewCards.setOptionalParams(0);


    }

    @Test(expected = NullPointerException.class)
    public void execute() throws Exception {

        effectOnNewCards.execute(player,game);


    }

}