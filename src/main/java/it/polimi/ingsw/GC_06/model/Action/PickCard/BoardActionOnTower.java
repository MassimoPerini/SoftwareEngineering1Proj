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
public class BoardActionOnTower extends BoardAction {

    private Tower tower;
    private int index;
    private FamilyMember familyMember;
    private Action pickCard;

    public BoardActionOnTower(Player player, int index, Tower tower, FamilyMember familyMember) {

        super(familyMember, tower.getTowerFloor(index).getActionPlace().getPrice());
        if (player==null || tower==null || familyMember==null)
            throw new NullPointerException();

        this.index = index;
        this.tower = tower;
        this.familyMember = familyMember;
        this.pickCard = new PickCard(player, tower.getTowerFloor(index), tower);
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

        //Check posso piazzare il familiare (o virtuale)
        if (!tower.isAllowed(getFamilyMember(), index))
            return false;
        return pickCard.isAllowed();
        /*DA SEPARARE*/
        //Check board space

    }
}
