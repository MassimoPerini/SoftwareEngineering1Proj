package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnNewCards;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Created by gabri on 08/06/2017.
 */
public class EffectOnNeweCardsTest {
    private List<TowerFloor> selectableCards;
    private EffectOnNewCards effectOnNewCards;
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
        Map<String, Integer> res= new HashMap<>();
        res.put("GREEN", 4);
        effectOnNewCards = new EffectOnNewCards(res);


    }

    @Test (expected = NullPointerException.class)
    public void correctTransition() throws InterruptedException {
        effectOnNewCards.execute(player, game);
    }

}
