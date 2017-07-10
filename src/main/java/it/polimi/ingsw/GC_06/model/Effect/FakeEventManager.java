package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Network.ServerOrchestrator;
import it.polimi.ingsw.GC_06.model.Card.ExcomunicationCard;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.GameEventManager;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 7/2/17.
 */
public class FakeEventManager implements GameEventManager{

    private Game game;
    private ServerOrchestrator serverOrchestrator;

    public FakeEventManager(Game game, ServerOrchestrator serverOrchestrator) {
        this.game = game;
        this.serverOrchestrator = serverOrchestrator;
    }

    @Override
    public List<Player> newTurn(int turn) {
        return null;
    }

    @Override
    public void newEra(int era) {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void start() {

    }

    @Override
    public Map<Integer, List<ExcomunicationCard>> getExcomunicationCards() {
        return null;
    }
}
