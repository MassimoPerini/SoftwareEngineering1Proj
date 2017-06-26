package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.State.Game;

/**
 * Created by massimo on 25/06/17.
 */
public class EndTurn implements Action {

    private final Game game;

    public EndTurn(Game game)
    {
        this.game = game;
    }


    @Override
    public void execute() {
        game.endTurn();
    }

    @Override
    public boolean isAllowed() {
        return true;
    }

}
