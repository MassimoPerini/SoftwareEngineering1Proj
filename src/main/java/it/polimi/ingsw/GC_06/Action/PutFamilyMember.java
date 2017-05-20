package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.Board.Board;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public class PutFamilyMember implements Action{

    private Player player;
    private FamilyMember familyMember;
    private Board board;


    public PutFamilyMember(Player player, FamilyMember familyMember,  board) {
        this.player = player;
        this.familyMember = familyMember;
        this.board = board;
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isAllowed() {

    }
}
