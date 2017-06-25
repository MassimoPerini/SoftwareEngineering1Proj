package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giuseppe on 6/4/17.
 */
public class BonusMalusHandler {

    /** se mi vengono passati due resourceSet dobbiamo settare il BonusMalusEntity*/

    /** filtro sulle azioni torre e specifiche per un colore */

    public static List<Integer> filter(Player player, ActionType actionType, String towerColour, FamilyMember familyMember){

        ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION);
        List<Integer> nonPermanentMalus = new LinkedList<>();
        System.out.println("ciao");
        for(int i=0; i<bonusMalusOnActions.size();i++){
            BonusMalusOnAction bonusMalusOnAction = bonusMalusOnActions.get(i);
            if(bonusMalusOnAction.isAllowed(familyMember,actionType)&& bonusMalusOnAction.getColourTarget().equals(towerColour)){
                bonusMalusOnAction.modify(familyMember);
                if(!bonusMalusOnAction.isPermanent()){
                    /** questa è la lista dei non permanent malus che sono stati lanciati durante l'azione del giocoatore */
                    nonPermanentMalus.add(i);
                }
            }
        }

        return nonPermanentMalus;

    }

    /** filtro sulle azioni generiche che attaccano qualsiasi familiare*/

    public static void filter(Player player, ActionType actionType, FamilyMember familyMember){

        ArrayList<BonusMalusOnAction> bonusMalusOnActions = player.getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION);

        for(int i = 0; i< bonusMalusOnActions.size();i++){

            BonusMalusOnAction bonusMalusOnAction = bonusMalusOnActions.get(i);
            if(bonusMalusOnAction.isAllowed(familyMember,actionType)){
                bonusMalusOnAction.modify(familyMember);
            }
        }
    }

    /** questo sarà il filtro che si applicherà sulle variazioni di risorse */
    public static void filter(Player player, ActionType actionType, ResourceSet targetResourceSet) {
        ArrayList<BonusMalusOnResources> bonusMalusOnResources = player.getBonusMalusSet().getBonusMalusOnResources().get(BonusMalusType.BONUSMALUSONRESOURCE);

        for (int i = 0; i < bonusMalusOnResources.size(); i++) {

            BonusMalusOnResources bonusMalusOnResource = bonusMalusOnResources.get(i);
            if (bonusMalusOnResource.isAllowed(actionType)) {
                bonusMalusOnResource.modify(targetResourceSet);
               i =  player.getBonusMalusSet().removeBonusMalusResources(bonusMalusOnResources,i);
            }


        }
    }
    /**filtro sui bonussui settaggi*/
    public static int filter(Player player,ActionType actionType,int endpoints, String colour){

        ArrayList<BonusMalusOnSettings> bonusMalusOnSettings = player.getBonusMalusSet().getBonusMalusOnSetting().get(BonusMalusType.BONUSMALUSONSETTING);
        int newValue = endpoints;
        for(int i = 0; i < bonusMalusOnSettings.size(); i++){
            BonusMalusOnSettings bonusMalusOnEnd = bonusMalusOnSettings.get(i);
            if(bonusMalusOnEnd.isAllowed(colour,actionType)){
               newValue =  bonusMalusOnEnd.modify(endpoints);
                i = player.getBonusMalusSet().removeBonusMalusSetting(bonusMalusOnSettings,i);
            }
        }

        return newValue;
    }

    /** ultimo filtro è quello sui costi*/

    public static void filter(Player player, ActionType actionType, DevelopmentCard developmentCard){
        ArrayList<BonusMalusOnCost> bonusMalusOnCosts = player.getBonusMalusSet().getBonusMalusOnCost().get(BonusMalusType.BONUSMALUSONCOST);

        for(int i = 0; i < bonusMalusOnCosts.size();i++){
            BonusMalusOnCost bonusMalusOnCost = bonusMalusOnCosts.get(i);
            if(bonusMalusOnCost.isAllowed(actionType)){
                bonusMalusOnCost.modify(developmentCard);
                i = player.getBonusMalusSet().removeBonusMalusCost(bonusMalusOnCosts,i);
            }
        }

    }

    /** filtro per attivare tutti i bonus una volta per turno della carta */
    /** filtri sull'accesso*/

    public static void filter(Player player,ActionType actionType,boolean result){
        ArrayList<BonusMalusOnAccess> bonusMalusOnAccesses = player.getBonusMalusSet().getBonusMalusOnAccess().get(BonusMalusType.BONUSMALUSONACCESS);
        for(int i = 0; i < bonusMalusOnAccesses.size();i++){
            BonusMalusOnAccess bonusMalusOnAccess = bonusMalusOnAccesses.get(i);

            if(bonusMalusOnAccess.isAllowed(actionType)){
                bonusMalusOnAccess.modify(result);
                i = player.getBonusMalusSet().removeBonusMalusAccess(bonusMalusOnAccesses,i);
            }

        }
    }

    /**filtro per attivare i bonus e malus di fine turno*/

    public static void filter(Player player, ResourceSet resourceSet,ActionType actionType){

        ArrayList<BonusMalusOnEnd> bonusMalusOnEnds = player.getBonusMalusSet().getBonusMalusOnEnd().get(BonusMalusType.BONUSMALUSONEND);

        for (BonusMalusOnEnd bonusMalusOnEnd : bonusMalusOnEnds) {
            for(int i = 0; i< bonusMalusOnEnds.size();i++){
                if(bonusMalusOnEnd.check(actionType,resourceSet)){
                    bonusMalusOnEnd.modify(player,resourceSet);
                    i = player.getBonusMalusSet().removeBonusMalusEnd(bonusMalusOnEnds,i);
                }
            }
        }
    }

}



