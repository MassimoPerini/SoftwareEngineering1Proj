package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;
import java.util.List;

public class Market implements Component
{
	private ArrayList<ActionPlace> actionPlaces;
	
	public Market(ArrayList<ActionPlace> places) {
		this.actionPlaces = places;
	}

	@Override
	public void addFamilyMember(FamilyMember familyMember, int index) {
		actionPlaces.get(index).addFamilyMember(familyMember);
	}

	@Override
	public boolean isAllowed(FamilyMember familyMember, int index) {

		return actionPlaces.get(index).isAllowed(familyMember);
	}

	@Override
	public List<Effect> getEffect(int index) {
		return actionPlaces.get(index).getEffects();
	}
}
