package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public class BoardActionOnTower extends Action {

    private Tower tower;
    private int index;
    private Action pickCard;
    private FamilyMember familyMember;

    public BoardActionOnTower(Player player, int index, Tower tower, FamilyMember familyMember) {
        super("actionOnTower", familyMember.getValue());
        if (player==null || tower==null)
            throw new NullPointerException();

        this.familyMember = familyMember;
        this.index = index;
        this.tower = tower;
        this.familyMember = familyMember;
        this.pickCard = new PickCard(player, tower.getTowerFloor().get(index), tower, super.getValueAction());
    }

    @Override
    public void execute() {

        if (!isAllowed())
            throw new IllegalStateException();


        tower.getTowerFloor().get(index).addFamilyMember(familyMember);

        pickCard.execute();



    }

    @Override
    public boolean isAllowed() {

        /** è permessa solo quando non c'è un familiare sulla torre*/

        return pickCard.isAllowed();

    }
}
