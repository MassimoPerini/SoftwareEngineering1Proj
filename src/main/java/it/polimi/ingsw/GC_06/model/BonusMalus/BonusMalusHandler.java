package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 6/4/17.
 */
public class BonusMalusHandler {

    /** filtro sulle azioni torre e specifiche per un colore */

    public static void filter(Player player, ActionType actionType, String towerColour, FamilyMember familyMember){

        ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get("ACTIONBONUSMALUS");

        for(BonusMalusOnAction bonusMalusOnAction : bonusMalusOnActions){
            if(bonusMalusOnAction.getActionType().equals(actionType) && bonusMalusOnAction.getColourTarget().equals(towerColour)
                    && bonusMalusOnAction.checkFamilyMember(familyMember)){
                bonusMalusOnAction.modify(familyMember);
            }
        }
    }

    /** filtro sulle azioni generiche che attaccano qualsiasi familiare*/

    public static void filter(Player player, ActionType actionType, FamilyMember familyMember){

        ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get("ACTIONBONUSMALUS");

        for(BonusMalusOnAction bonusMalusOnAction : bonusMalusOnActions){
            if(bonusMalusOnAction.getActionType().equals(actionType) && bonusMalusOnAction.checkFamilyMember(familyMember)){
                bonusMalusOnAction.modify(familyMember);
            }
        }
    }

    /** questo sarà il filtro che si applicherà sulle variazioni di risorse */
    public static void filter(Player player, ActionType actionType, ResourceSet targetResourceSet){
        ArrayList<BonusMalusOnResources> bonusMalusOnResources = player.getBonusMalusSet().getBonusMalusOnResources().get("RESOURCEBONUSMALUS");

        for(BonusMalusOnResources bonusMalusOnResource : bonusMalusOnResources){
            if(bonusMalusOnResource.getActionType().equals(actionType)){
                bonusMalusOnResource.modify(targetResourceSet);
            }
        }
    }
    /**filtro sui bonus e i malus di fine turno*/
    public static void filter(Player player,ActionType actionType,ResourceSet resourceSet, String colour){

        ArrayList<BonusMalusOnEnd> bonusMalusOnEnds = player.getBonusMalusSet().getBonusMalusOnEnd().get("ENDBONUSMALUS");

        for(BonusMalusOnEnd bonusMalusOnEnd : bonusMalusOnEnds){
            if(bonusMalusOnEnd.getActionType().equals(actionType) && bonusMalusOnEnd.getColour().equals(colour)){
                bonusMalusOnEnd.modify(resourceSet);
            }
        }
    }

    /** ultimo filtro è quello sui costi*/




}



