package it.polimi.ingsw.GC_06.model.Board;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

public class ActionPlace {
    private List<Effect> effects;
	private ArrayList<FamilyMember> members;
	private int price;
	
	public ActionPlace(List<Effect> effect, int price) {
		if (effect == null)
			throw new NullPointerException();
		this.effects = effect;
		this.members = new ArrayList<>();
		this.price = price;
		
	}

	//TODO N.B. Sarebbe meglio una delega???

	public boolean isAllowed (FamilyMember member)
	{
		if (member == null)
			throw new NullPointerException();
		return member.getValue() >= price;
	}

    public List<Effect> addFamilyMember(FamilyMember familyMember)
	{
		if (!isAllowed(familyMember))
			throw new IllegalArgumentException();
		this.members.add(familyMember);
		return effects;
	}

	public ArrayList<FamilyMember> getMembers() {
		return members;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public int getPrice() {
		return price;
	}
}
