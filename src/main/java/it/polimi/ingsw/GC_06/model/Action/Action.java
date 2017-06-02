package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.Set;

/**
 * Created by massimo on 26/05/17.
 */
public abstract class Action {

    private final String idAction;
    private int valueAction;
    private Player player;

    public Action(String idAction, int valueAction)
    {
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
