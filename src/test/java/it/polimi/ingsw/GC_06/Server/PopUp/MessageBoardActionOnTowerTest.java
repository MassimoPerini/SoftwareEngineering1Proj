package it.polimi.ingsw.GC_06.Server.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by giuseppe on 7/1/17.
 */
public class MessageBoardActionOnTowerTest {

    private String tower;
    private int floor;
    private int familyMember;
    private int powerUpValue;
    private MessageBoardActionTower messageBoardActionTower;
    private Game game;
    private Player player;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");


        tower = "YELLOW";
        floor = 2;
        familyMember = 1;
        powerUpValue = 100;
        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());
        player = game.getGameStatus().getPlayers().get("peppe");

        List<String> gamers = new LinkedList<>();

        Set<String> player = game.getGameStatus().getPlayers().keySet();
        for (String s : player) {
            gamers.add(s);
        }
        GameList.getInstance().add(game, gamers);

        messageBoardActionTower = new MessageBoardActionTower(tower,floor,familyMember,powerUpValue);
        messageBoardActionTower.setPlayer("peppe");
        messageBoardActionTower.setGame(0);

    }

    @Test
    public void firstTest(){
     /*   messageBoardActionTower.execute();
        assertTrue(player.getPlayerBoard().getDevelopmentCards("YELLOW").size() == 1);*/
    }


}
