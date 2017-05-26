package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class Council implements Component {
	private ArrayList<ActionPlace> actionPlaces;
	
	public Council(ArrayList<ActionPlace> actionPlaces) {
		this.actionPlaces = actionPlaces;
	}

	@Override
	public void addFamilyMember(FamilyMember familyMember, int index) {
		if (!isAllowed(familyMember, index))
			throw new IllegalStateException();
		this.actionPlaces.get(index).addFamilyMember(familyMember);
	}

	@Override
	public boolean isAllowed(FamilyMember familyMember, int index) {
		return actionPlaces.get(index).isAllowed(familyMember);
	}

	@Override
	public ArrayList<Effect> getEffect(int index) {
		return actionPlaces.get(index).getEffects();
	}


}
