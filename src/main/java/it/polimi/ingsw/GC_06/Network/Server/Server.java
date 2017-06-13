package it.polimi.ingsw.GC_06.Network.Server;

import it.polimi.ingsw.GC_06.model.State.Game;

import java.io.IOException;
import java.util.Observable;

/**
 * Created by massimo on 13/06/17.
 */
public abstract class Server extends Observable {
    public abstract void start();
    public abstract void startGame(Game game);
}
