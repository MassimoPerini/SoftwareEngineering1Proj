package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 6/4/17.
 */
public class BonusMalusHandler {


    private static  boolean checkBonusMalus(Player player){

        return true;

    }

    // questo è il primo filter che chiamerà i bonus e i malus sulla action
    public static void filter(Player player, Action action) {

        /** if (true) {

            // mi deve eseguire tutti i bonus e i malus

            ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get(playType);

            for (BonusMalusOnAction bonusMalusOnAction : bonusMalusOnActions) {
                bonusMalusOnAction.modify(action.getValueAction());
            }
        }

        System.out.println("Work in progress");*/
    }

    // questo viene chiamato sulle modifiche alle risorse
    public static void filter(Player player, ResourceSet targetResourceSet){

        /**if (true){

            ArrayList<BonusMalusOnResources> bonusMalusOnResources = player.getBonusMalusSet().getBonusMalusOnResources().get(playType);

            for(BonusMalusOnResources bonusMalusOnResource : bonusMalusOnResources){
                if(playType.equals(playType)){
                    bonusMalusOnResource.modify(targetResourceSet);
                }
            }

        }

    }

    public static void filter(){}
*/
}
}

