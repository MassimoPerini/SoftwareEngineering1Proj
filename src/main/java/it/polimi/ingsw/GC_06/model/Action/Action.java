package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.GameStatus;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 26/05/17.
 */
public abstract class Action {

    private BonusMalusHandler bonusMalusHandler;
    private PlayType playType;
    private int valueAction;
    private Player player;
    protected GameStatus status;

    public Action(PlayType playType, int valueAction, BonusMalusHandler bonusMalusHandler)
    {
        super();
        this.bonusMalusHandler = bonusMalusHandler;
        this.status = Game.getInstance().getGameStatus();
        this.valueAction = valueAction;
        this.playType = playType;
    }

    public abstract void execute();
    public abstract boolean isAllowed();

    public int getValueAction() {
        return valueAction;
    }

    public void setValueAction(int valueAction) {
        this.valueAction = valueAction;
    }

    public PlayType getPlayType() {
        return playType;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BonusMalusHandler getBonusMalusHandler() {
        return bonusMalusHandler;
    }

    public Player getPlayer() {
        return player;
    }
}
