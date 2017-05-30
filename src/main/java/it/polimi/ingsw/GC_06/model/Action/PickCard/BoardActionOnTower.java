package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.BoardAction;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by giuseppe on 5/20/17.
 */
public class BoardActionOnTower implements Action {

    private Tower tower;
    private int index;
    private Action pickCard;
    private FamilyMember familyMember;
    private int value;

    public BoardActionOnTower(Player player, int index, Tower tower, FamilyMember familyMember) {

        super();
        if (player==null || tower==null || familyMember==null)
            throw new NullPointerException();

        this.familyMember = familyMember;
        this.value = familyMember.getValue();
        this.index = index;
        this.tower = tower;
        this.familyMember = familyMember;
        this.pickCard = new PickCard(player, tower.getTowerFloor(index), tower, familyMember.getValue());
    }

    @Override
    public void execute() {

        if (!isAllowed())
            throw new IllegalStateException();

        tower.getTowerFloor(index).addFamilyMember(familyMember);

        pickCard.execute();

    }

    @Override
    public boolean isAllowed() {

        /** è permessa solo quando non c'è un familiare sulla torre*/

        return pickCard.isAllowed();

    }
}
