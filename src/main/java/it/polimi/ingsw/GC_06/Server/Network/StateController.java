package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by giuseppe on 6/16/17.
 */
public class StateController implements Observer {

    private Game game;

    public StateController(Game game) {
        this.game = game;
    }

    public void start(){
        /** aggiungiamo un observer sul cambio di stato*/
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
