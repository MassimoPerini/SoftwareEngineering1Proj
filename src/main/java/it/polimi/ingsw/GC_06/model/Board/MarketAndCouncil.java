package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;


public class MarketAndCouncil extends Observable
{
	private final ArrayList<ActionPlace> actionPlaces;
	private final ActionType actionType;
	
	public MarketAndCouncil(ArrayList<ActionPlace> places, ActionType actionType) {
		this.actionPlaces = places;
		this.actionType = actionType;
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

	void removeFamilyMembers()
	{
		for (ActionPlace actionPlace : actionPlaces) {
			actionPlace.removeFamilyMembers();
		}
	}

	public ActionType getActionType() {
		return actionType;
	}

	public List<ActionPlace> getActionPlaces() {
		return Collections.unmodifiableList(actionPlaces);
	}
}
