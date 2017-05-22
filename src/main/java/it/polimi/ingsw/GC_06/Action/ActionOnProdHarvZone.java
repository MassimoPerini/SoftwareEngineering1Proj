package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Card.Card;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
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
    private Colour colour;
    //TODO da cambiare


    public ActionOnProdHarvZone(int point, Player player, FamilyMember familyMember, Colour colour) {
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

        LinkedList<Card> colouredCards = new LinkedList<Card>();

        colouredCards = player.getCardSet().getColouredCards();

        // for where we throw effects

    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
