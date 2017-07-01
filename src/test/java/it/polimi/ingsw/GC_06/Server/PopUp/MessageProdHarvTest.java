package it.polimi.ingsw.GC_06.Server.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.Client.MessageProdHarv;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static sun.audio.AudioPlayer.player;

/**
 * Created by giuseppe on 7/1/17.
 */
public class MessageProdHarvTest {

    private Game game;
    private MessageProdHarv messageProdHarv;
    private Player player;


    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());

        player = game.getGameStatus().getPlayers().get("peppe");



        List<String> gamers = new LinkedList<>();

        Set<String> player = game.getGameStatus().getPlayers().keySet();
        for (String s : player) {
            gamers.add(s);
        }
        GameList.getInstance().add(game, (List<String>) gamers);



        messageProdHarv = new MessageProdHarv(0,0,0,100);
        messageProdHarv.setPlayer("peppe");



    }

    @Test
    public void firsTest(){

        messageProdHarv.execute();
        assertTrue(player.getFamilyMembers()[0].isAlreadyUsed());
    }
}
