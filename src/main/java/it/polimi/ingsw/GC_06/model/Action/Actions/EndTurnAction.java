package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;

/**
 * Created by giuseppe on 5/31/17.
 */
public class EndTurnAction implements Action {

    public Game game;
    public EndTurnAction() {
        super( );

    }

    @Override
    public void execute() {



        game.endTurn();
        game.getGameStatus().changeState(TransitionType.ENDTURN);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
