package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class Council implements Component {
	private ArrayList<SmallActionPlace> smallActionPlaces;
	
	public Council(ArrayList<SmallActionPlace> smallActionPlaces) {
		this.smallActionPlaces = smallActionPlaces;
	}

	@Override
	public void addFamilyMember(FamilyMember familyMember, int index) {
		if (!isAllowed(familyMember, index))
			throw new IllegalStateException();
		this.smallActionPlaces.get(index).addFamilyMember(familyMember);
	}

	@Override
	public boolean isAllowed(FamilyMember familyMember, int index) {
		return smallActionPlaces.get(index).isAllowed(familyMember);
	}

	@Override
	public ArrayList<Effect> getEffect(int index) {
		return smallActionPlaces.get(index).getEffects();
	}


}
