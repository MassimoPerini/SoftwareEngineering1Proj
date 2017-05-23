package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Effect.Effect;

public class ActionPlace {
    private ArrayList<Effect> effects;
	private ArrayList<FamilyMember> members;
	private int price;
	
	public ActionPlace(ArrayList<Effect> effect, int price) {
		this.effects = effect;
		this.members = new ArrayList<>();
		this.price = price;
		
	}

	//TODO N.B. Sarebbe meglio una delega???

	public boolean isAllowed (FamilyMember member)
	{
		return member.getValue() >= price;
	}

    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember)
	{
		if (!isAllowed(familyMember))
			throw new IllegalStateException();
		this.members.add(familyMember);
		return effects;
	}

	public ArrayList<FamilyMember> getMembers() {
		return members;
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}
}
