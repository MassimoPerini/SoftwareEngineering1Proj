package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.StateName;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 14/06/17.
 */
public class ControllerUser implements Observer {

    private final ServerOrchestrator serverOrchestrator;
    private final Game game;

    public ControllerUser(ServerOrchestrator serverOrchestrator, Game game)
    {
        this.serverOrchestrator = serverOrchestrator;
        this.game = game;
    }

    public void start()
    {
        game.getStatuses().get(StateName.IDLE).addObserver(this);
        game.getStatuses().get(StateName.TURN_ACTION_COMPLETED).addObserver(this);
        //finiamo questo
    }

    @Override
    public void update(Observable o, Object arg) {
        serverOrchestrator.send(game.getCurrentPlayer().getPLAYER_ID(), arg);
    }
}
