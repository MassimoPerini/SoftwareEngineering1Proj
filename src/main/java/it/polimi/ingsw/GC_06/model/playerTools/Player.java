package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.model.Board.PlayerBoard;
import it.polimi.ingsw.GC_06.model.Card.Card;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/19/17.
 */
public class Player {

    private CardSet cardSet;
    private PlayerBoard playerBoard;
    private FamilyMember [] familyMembers;
    private ResourceSet resourceSet;
    private final String PLAYER_ID;
    private final static String TOKEN = "n_family_members";
    private ArrayList<Effect> bonusMalus;

    //TODO sistemare la questione dei malus e bonus sul player

    public Player(String PLAYER_ID, FamilyMember[] familyMembers) {


        this.PLAYER_ID = PLAYER_ID;
        this.resourceSet = new ResourceSet();
        this.cardSet = new CardSet();
        this.familyMembers = familyMembers;
    }

    public Player (Player p)
    {
        this.resourceSet = new ResourceSet(p.getResourceSet());
        this.PLAYER_ID = p.getPLAYER_ID();
        //TODO complete here...
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public String getPLAYER_ID() {
        return PLAYER_ID;
    }

    public void add(Card card){
     //   cardSet.addCard(card);
    }

    public FamilyMember[] getFamilyMembers() {
        return familyMembers;
    }

}
