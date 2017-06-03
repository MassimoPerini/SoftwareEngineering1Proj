package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.GameStatus;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.Set;

/**
 * Created by massimo on 26/05/17.
 */
public abstract class Action {

    private final String idAction;
    private int valueAction;
    private Player player;
    protected GameStatus status;

    public Action(String idAction, int valueAction)
    {
        super();
        this.status = Game.getInstance().getGameStatus();
        this.valueAction = valueAction;
        this.idAction = idAction;
    }

    public abstract void execute();
    public abstract boolean isAllowed();

    public int getValueAction() {
        return valueAction;
    }

    public void setValueAction(int valueAction) {
        this.valueAction = valueAction;
    }

    public String getIdAction() {
        return idAction;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private boolean checkBonusMalus(){

        Set<String> keySet =  player.getBonusMalusSet().getBonusMalusOnAction().keySet();

        for(String key : keySet){
            if(key.equals(this.idAction)){
                return true;
            }
        }

        return false;
    }
}
