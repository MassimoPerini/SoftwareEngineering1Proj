package it.polimi.ingsw.GC_06.Action;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Board.Component;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 */
public abstract class Action {
	private int value;
	private FamilyMember familyMember;

    public abstract void  execute();
    public abstract boolean isAllowed();
    public Action(FamilyMember familyMember) {
    	this.familyMember = familyMember;
    	this.value = familyMember.getValue();
    }
    public int getValue() {
    	return this.value;
    }
    public FamilyMember getFamilyMember() {
    	return this.familyMember;
    }
}
