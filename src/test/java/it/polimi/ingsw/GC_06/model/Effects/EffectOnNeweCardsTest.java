package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnNewCards;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.StateName;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 08/06/2017.
 */
public class EffectOnNeweCardsTest {
    private List<TowerFloor> selectableCards;
    private EffectOnNewCards effectOnNewCards;
    private Player player;

    @Before
    public void setUp() throws IOException {
        Game game = new Game();
        game.addPlayer("gabriele");
        player = game.getGameStatus().getPlayers().get("gabriele");
        selectableCards = new ArrayList<>();
        selectableCards = game.getBoard().getTowers().get("GREEN").getTowerFloor();
        effectOnNewCards = new EffectOnNewCards(selectableCards);
    }

    @Test
    public void correctTransition() {
        //effectOnNewCards.execute(player);
        //assertTrue(Game.getInstance().getGameStatus().getCurrentStatus()== StateName.CHOOSE_CARD);
        //TODO da implementare la tabella delle transisioni
    }
}
