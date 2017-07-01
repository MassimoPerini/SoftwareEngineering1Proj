package it.polimi.ingsw.GC_06.Server.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMarketCouncil;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.State.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by giuseppe on 7/1/17.
 */
public class MessageMarketCouncilTest {

    private Game game;
    private MessageMarketCouncil messageMarketCouncil;

    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());

        game.getGameStatus().getPlayers().get("peppe").getFamilyMembers()[0].setValue(100);

        messageMarketCouncil = new MessageMarketCouncil(0,0,0);
        messageMarketCouncil.setGame(0);
        messageMarketCouncil.setPlayer("peppe");

        List<String> gamers = new LinkedList<>();

        Set<String> player = game.getGameStatus().getPlayers().keySet();
        for (String s : player) {
            gamers.add(s);
        }
        GameList.getInstance().add(game, (List<String>) gamers);

    }

    @Test
    public void firstTest(){

        messageMarketCouncil.execute();

    }
}
