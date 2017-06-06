package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MarketAndCouncil
{
	private final ArrayList<ActionPlace> actionPlaces;
	
	public MarketAndCouncil(ArrayList<ActionPlace> places) {
		this.actionPlaces = places;
	}

	public void addFamilyMember(FamilyMember familyMember, int index) {
		actionPlaces.get(index).addFamilyMember(familyMember);
	}

	public boolean isAllowed(FamilyMember familyMember, int index) {

		return actionPlaces.get(index).isAllowed(familyMember);
	}

	public List<Effect> getEffects(int index)
	{
		return Collections.unmodifiableList(actionPlaces.get(index).getEffects());
	}

}
