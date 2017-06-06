package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 6/4/17.
 */
public class BonusMalusHandler {


        /** questo metodo si occuperà di verificare se il player ha dei bonus o malus che gli devono essere applicati*/



    /** questo sarà il filtro dei bonus e malus*/
    public static void filter(Player player, FamilyMember familyMember, ActionType actionTarget) {

        ArrayList<BonusMalusOnAction> bonusMalusOnActions =  player.getBonusMalusSet().getBonusMalusOnAction().get("ACTION");

        for(BonusMalusOnAction bonusMalusOnAction : bonusMalusOnActions){
            /** verifico che quel malus è riferito a quel tipo di azione*/
            if(bonusMalusOnAction.getActionType().equals(actionTarget)){
                /** superato il controllo eseguo il malus o il bonus*/
                bonusMalusOnAction.modify(familyMember);
            }
        }

    }

    /** questo sarà il filtro sugli effetti */
    public static void filter(Player player, ResourceSet targetResourceSet, ActionType effectTarget){

            ArrayList<BonusMalusOnResources> bonusMalusOnResources = player.getBonusMalusSet().getBonusMalusOnResources().get("RESOURCE");

            for(BonusMalusOnResources bonusMalusOnResource : bonusMalusOnResources){
                /** verifico che il malus o il bonus è riferito a quel tipo di effetto */
                if(bonusMalusOnResource.getActionType().equals(effectTarget)){
                    /** superato il controllo eseguo il malus o il bonus*/
                    bonusMalusOnResource.modify(targetResourceSet);
                }
            }

    }

    /** questa è l'ultima tipologia di malus quelli che modificano la donazione di punti fede alla fine del gioco
     * ( a me non piace quindi molto probabilmente la cambierò)
     */
    public static void filter(int size, String developmentCard_ID,Player player){
       ArrayList<BonusMalusOnConditions> bonusMalusOnConditions= player.getBonusMalusSet().getBonusMalusOnConditions().get("CONDITIONAL");

       for(BonusMalusOnConditions bonusMalusOnCondition : bonusMalusOnConditions){
           bonusMalusOnCondition.modify(size,developmentCard_ID);
       }
    }

}



