package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Card.ExcomunicationCard;
import it.polimi.ingsw.GC_06.model.State.GameEventManager;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by giuseppe on 6/28/17.
 */
public class DefaulEventManagerFake implements GameEventManager {

    public DefaulEventManagerFake() {
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
