package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Board.PlayerBoard;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.Card.Card;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/19/17.
 * @author giuseppe
 * This class is a Player of the game
 */
public class Player {

    private final PlayerBoard playerBoard;
    private final FamilyMember [] familyMembers;
    private final ResourceSet resourceSet;
    private final String PLAYER_ID;
    private ArrayList<Effect> bonusMalus;
    private ResourceSet addAtTheEnd;
    private BonusMalusSet bonusMalusSet;

    //TODO sistemare la questione dei malus e bonus sul player

    public Player(String PLAYER_ID, FamilyMember[] familyMembers) {


        this.PLAYER_ID = PLAYER_ID;
        this.resourceSet = new ResourceSet();
        this.playerBoard = new PlayerBoard();
        this.familyMembers = familyMembers;
    }

    public Player (Player p)
    {
        this.resourceSet = new ResourceSet(p.getResourceSet());
        this.PLAYER_ID = p.getPLAYER_ID();
        this.playerBoard = p.playerBoard;
        this.familyMembers = p.familyMembers;
        //TODO complete here...
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public String getPLAYER_ID() {
        return PLAYER_ID;
    }

    public BonusMalusSet getBonusMalusSet() {
        return bonusMalusSet;
    }


    public FamilyMember[] getFamilyMembers() {
        return familyMembers;
    }

    public void variateResource(ResourceSet resourceSet) throws IllegalArgumentException
    {
        if (this.getResourceSet().isIncluded(resourceSet))
            this.getResourceSet().variateResource(resourceSet);
        else {
            throw new IllegalArgumentException();

        }
    }

    public ResourceSet getAddAtTheEnd() {
        return addAtTheEnd;
    }
}
