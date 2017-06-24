package it.polimi.ingsw.GC_06.model.State;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 24/06/17.
 */
public interface GameEventManager {

    List<Player> newTurn(int turn);
    void newEra (int era);
    void endGame();

}
