package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Action.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class Council implements Component {
	private ActionPlace actionPlace;
	
	public Council(ActionPlace actionPlace) {
		this.actionPlace = actionPlace;
	}

	@Override
	public ArrayList<Effect> addFamilyMember(FamilyMember familyMember, int index) {
		return false;
	}

	@Override
	public boolean isAllowed(FamilyMember familyMember, int index) {
		return false;
	}
}
