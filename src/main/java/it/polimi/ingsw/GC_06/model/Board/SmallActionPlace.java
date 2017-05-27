package it.polimi.ingsw.GC_06.model.Board;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

public class SmallActionPlace implements ActionPlace {
    private ArrayList<Effect> effects;
	private ArrayList<FamilyMember> members;
	private int threshold;
	private boolean full;

	
	public SmallActionPlace(ArrayList<Effect> effect, int price, int maxFamilyMembers) {
		this.effects = effect;
		this.members = new ArrayList<>();
		this.threshold = price;
	}


	public boolean isAllowed (FamilyMember member)
	{
		return member.getValue() >= threshold;
	}

    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember)
	{
		if (!isAllowed(familyMember))
			throw new IllegalStateException();
		this.members.add(familyMember);
		full = true;
		return effects;
	}



	public ArrayList<FamilyMember> getMembers() {
		return members;
	}

	public ArrayList<Effect> getEffects() {
		return effects;
	}

	public int getThreshold() {
		return threshold;
	}

	public boolean isFull(){
		return full;
	}


}
