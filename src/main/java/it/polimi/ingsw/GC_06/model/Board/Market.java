package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class Market implements Component
{
	private ArrayList<SmallActionPlace> smallActionPlaces;
	
	public Market(ArrayList<SmallActionPlace> places) {
		this.smallActionPlaces = places;
	}

	@Override
	public void addFamilyMember(FamilyMember familyMember, int index) {
		smallActionPlaces.get(index).addFamilyMember(familyMember);
	}

	@Override
	public boolean isAllowed(FamilyMember familyMember, int index) {

		return smallActionPlaces.get(index).isAllowed(familyMember);
	}

	public ArrayList<SmallActionPlace> getSmallActionPlaces() {
		return smallActionPlaces;
	}

	@Override
	public ArrayList<Effect> getEffect(int index) {
		return smallActionPlaces.get(index).getEffects();
	}


}
