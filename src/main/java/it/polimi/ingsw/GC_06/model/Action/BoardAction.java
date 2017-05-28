package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.FamilyMember;

/**
 * Created by giuseppe on 5/20/17.
 */
public abstract class BoardAction implements Action{
	private int value;
	private FamilyMember familyMember;

    public abstract void  execute();
    public abstract boolean isAllowed();
    public BoardAction(FamilyMember familyMember, int value) {
    	this.familyMember = familyMember;
    	this.value = value;
    }
    public int getValue() {
    	return this.value;
    }
    public FamilyMember getFamilyMember() {
    	return this.familyMember;
    }
}
