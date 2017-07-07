package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by giuseppe on 7/1/17.
 */
public class PowerUpTest {

    private Game game;
    private PowerUp powerUp;
    private Player player;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());

        player = game.getGameStatus().getPlayers().get("peppe");
        powerUp = new PowerUp();




    }


    @Test
    public void firstTest() throws InterruptedException {
/**
        powerUp.setPowerUp(5);
        powerUp.execute(game,player);
*/
    }

}