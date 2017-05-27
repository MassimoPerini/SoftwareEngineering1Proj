package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Board.Component;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class ActionOnProdHarvZone extends Action {

    private ProdHarvZone prodHarvZone;
    private Player player;
    private FamilyMember familyMember;
    private String colour;
    private int index;
    //TODO da cambiare TUTTO (familymember e prodHarvZone)


    public ActionOnProdHarvZone(Player player, FamilyMember familyMember, String colour, ProdHarvZone prodHarvZone, int value) {
        super(familyMember, value);
    	this.colour = colour; 
        this.player = player;
        this.prodHarvZone = prodHarvZone ;
    }



    @Override
    public void execute() {
        ArrayList<Effect> effects = new ArrayList<>();

        if(familyMember!=null){
            prodHarvZone.addFamilyMember(familyMember, index);
        }



        //select the cards from the player's cardSet


        /**  this is an array of development cards*/
        ArrayList<DevelopmentCard> colouredCards = player.getPlayerBoard().getColouredCards(colour);

        // for where we throw effects

        /**mi faccio un array dove contengo tutti gli effetti delle carte che voglio lanciare nella produzione */

        for(DevelopmentCard card : colouredCards){
            // potrebbe essere una cazzata
            /**chiediamo a massi*/
            effects  = card.getEffects();
        }

        for(Effect effect : effects){
            effect.execute(player);
        }

    }

    @Override
    public boolean isAllowed() {
    	//TODO cazzo peppe scrivi i todo

        return true;
    }
}
