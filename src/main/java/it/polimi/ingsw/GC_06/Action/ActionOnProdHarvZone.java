package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Card.Card;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Player;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ActionOnProdHarvZone implements Action {

    private int point;
    private Player player;
    private FamilyMember familyMember;
    private String colour;
    //TODO da cambiare


    public ActionOnProdHarvZone(int point, Player player, FamilyMember familyMember, String colour) {
        this.colour = colour;
        this.point = point;
        this.player = player;
        this.familyMember = familyMember;
    }



    @Override
    public void execute() {

        if(familyMember!=null){
            // aggiungi il familiare
        }


        //select the cards from the player's cardSet

        LinkedList<DevelopmentCard> colouredCards;
        ArrayList<Effect> effects = new ArrayList<Effect>();

        /**  this is an array of development cards*/
        colouredCards = player.getPlayerBoard().getColouredCards(colour);

        // for where we throw effects

        /**mi faccio un array dove contengo tutti gli effetti delle carte che voglio lanciare nella produzione */

        for(DevelopmentCard card : colouredCards){
            // potrebbe essere una cazzata
            /**chiediamo a massi*/
            ArrayList<Effect> effects  = card.getEffects();
        }

        for(Effect effect : effects){
            effect.execute(player);
        }

    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
