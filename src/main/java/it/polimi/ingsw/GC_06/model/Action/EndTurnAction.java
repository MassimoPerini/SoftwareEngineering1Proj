package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.ConversionTable;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

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
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
