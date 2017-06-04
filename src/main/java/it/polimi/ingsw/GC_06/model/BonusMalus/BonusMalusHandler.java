package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.PlayType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by giuseppe on 6/4/17.
 */
public class BonusMalusHandler {

    private boolean checkBonusMalus(Player player,PlayType playType){

       Set<PlayType> actionKeySet =  player.getBonusMalusSet().getBonusMalusOnAction().keySet();
       for(PlayType key : actionKeySet){
           if(key.equals(playType)){
               return true;
           }
       }

       return false;

    }

    // questo è il primo filter che chiamerà i bonus e i malus sulla action
    public void filter(Player player, PlayType playType, Action action) {

        if (checkBonusMalus(player, playType)) {

            // mi deve eseguire tutti i bonus e i malus

            ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get(playType);

            for (BonusMalusOnAction bonusMalusOnAction : bonusMalusOnActions) {
                bonusMalusOnAction.modify(action.getValueAction());
            }

            System.out.println("Work in progress");
        }
    }


}
