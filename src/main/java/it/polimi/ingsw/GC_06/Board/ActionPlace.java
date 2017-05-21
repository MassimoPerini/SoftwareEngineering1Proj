package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Action.Effect;

public abstract class ActionPlace {
    protected ArrayList<Effect> effects;
	protected ArrayList<FamilyMember> members;
	protected int price;
	
	public ActionPlace(ArrayList<Effect> effect, int price) {
		this.effects = effect;
		this.members = new ArrayList<>();
		this.price = price;
		
	}

	//TODO N.B. Sarebbe meglio una delega???
	
	protected boolean isValidColor(FamilyMember member) {
		for (FamilyMember f : members) {
			if (f.getColour() == member.getColour()) {
				return false;
			}
		}
		return true;
	}

	public boolean isAllowed(FamilyMember member){

		return isValidColor(member);
	}

    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember)
	{
		if (!isAllowed(familyMember))
			throw new IllegalStateException();
		else this.members.add(familyMember);
		return effects;
	}


}
