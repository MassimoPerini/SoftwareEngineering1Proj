package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnParchment;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnParchmentTest {
    private EffectOnParchment effectOnParchment;
    private ArrayList<ResourceSet> parchments;
    private Player player;
    private Game game;

    @Before
    public void setUp() throws IOException {
        Setting.getInstance().addPath("settings/bundle");
        game = new Game(1);
        game.init();
        game.addPlayer("gabriele");
        game.start(new DefaulEventManagerFake());
        player = game.getGameStatus().getPlayers().get("gabriele");
        effectOnParchment = new EffectOnParchment(1, false);

    }

    @Test (expected = NullPointerException.class)
    public void correctTransition() throws InterruptedException {
        effectOnParchment.execute(player, game);
    }


}
