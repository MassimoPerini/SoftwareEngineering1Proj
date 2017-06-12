package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 6/4/17.
 */
public class BonusMalusHandler {

    /** se mi vengono passati due resourceSet dobbiamo settare il BonusMalusEntity*/

    /** filtro sulle azioni torre e specifiche per un colore */

    public static void filter(Player player, ActionType actionType, String towerColour, FamilyMember familyMember){

        ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get("ACTIONBONUSMALUS");


        for(int i=0; i<bonusMalusOnActions.size();i++){
            BonusMalusOnAction bonusMalusOnAction = bonusMalusOnActions.get(i);
            if(bonusMalusOnAction.getActionType().equals(actionType) && bonusMalusOnAction.getColourTarget().equals(towerColour)
                    && bonusMalusOnAction.checkFamilyMember(familyMember)){
                bonusMalusOnAction.modify(familyMember);
                player.getBonusMalusSet().removeBonusMalusAction(bonusMalusOnActions,i);
            }
        }

    }

    /** filtro sulle azioni generiche che attaccano qualsiasi familiare*/

    public static void filter(Player player, ActionType actionType, FamilyMember familyMember){

        ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get("ACTIONBONUSMALUS");

        for(int i = 0; i< bonusMalusOnActions.size();i++){

            BonusMalusOnAction bonusMalusOnAction = bonusMalusOnActions.get(i);
            if(bonusMalusOnAction.getActionType().equals(actionType) && bonusMalusOnAction.checkFamilyMember(familyMember)){
                bonusMalusOnAction.modify(familyMember);
                player.getBonusMalusSet().removeBonusMalusAction(bonusMalusOnActions,i);
            }
        }
    }

    /** questo sarà il filtro che si applicherà sulle variazioni di risorse */
    public static void filter(Player player, ActionType actionType, ResourceSet targetResourceSet) {
        ArrayList<BonusMalusOnResources> bonusMalusOnResources = player.getBonusMalusSet().getBonusMalusOnResources().get("RESOURCEBONUSMALUS");

        for (int i = 0; i < bonusMalusOnResources.size(); i++) {

            BonusMalusOnResources bonusMalusOnResource = bonusMalusOnResources.get(i);
            if (bonusMalusOnResource.getActionType().equals(actionType)) {
                bonusMalusOnResource.modify(targetResourceSet);
                player.getBonusMalusSet().removeBonusMalusResources(bonusMalusOnResources,i);
            }


        }
    }
    /**filtro sui bonus e i malus di fine turno*/
    public static void filter(Player player,ActionType actionType,ResourceSet resourceSet, String colour){

        ArrayList<BonusMalusOnEnd> bonusMalusOnEnds = player.getBonusMalusSet().getBonusMalusOnEnd().get("ENDBONUSMALUS");

        for(int i = 0; i < bonusMalusOnEnds.size(); i++){
            BonusMalusOnEnd bonusMalusOnEnd = bonusMalusOnEnds.get(i);
            if(bonusMalusOnEnd.getActionType().equals(actionType) && bonusMalusOnEnd.getColour().equals(colour)){
                bonusMalusOnEnd.modify(resourceSet);
                player.getBonusMalusSet().removeBonusMalusEnd(bonusMalusOnEnds,i);
            }
        }
    }

    /** ultimo filtro è quello sui costi*/

    /** filtro per attivare tutti i bonus una volta per turno della carta */
    /** filtri sull'accesso*/

    public static void filter(Player player,ActionType actionType,boolean result){
        ArrayList<BonusMalusOnAccess> bonusMalusOnAccesses = player.getBonusMalusSet().getBonusMalusOnAccess().get("ACCESS");
        for(int i = 0; i < bonusMalusOnAccesses.size();i++){
            BonusMalusOnAccess bonusMalusOnAccess = bonusMalusOnAccesses.get(i);

            if(bonusMalusOnAccess.getActionType().equals(actionType)){
                bonusMalusOnAccess.modify(result);
                player.getBonusMalusSet().removeBonusMalusAccess(bonusMalusOnAccesses,i);
            }

        }
    }

}



