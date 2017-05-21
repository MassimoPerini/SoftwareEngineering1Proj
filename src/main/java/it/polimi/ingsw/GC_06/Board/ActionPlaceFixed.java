package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.FamilyMember;

public class ActionPlaceFixed extends ActionPlace {

	@Override
	public boolean isAllowed(FamilyMember member) {
		if (this.members.size() < 1) {
			return true;
		}
		return false;
	}

}
