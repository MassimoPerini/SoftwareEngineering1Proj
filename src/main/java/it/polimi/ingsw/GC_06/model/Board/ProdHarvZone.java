package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class ProdHarvZone extends Observable{

	private final List <ActionPlace> actionPlaces;
	private int maxSamePlayerFamilyMember;
	private ActionType actionType;

	public ProdHarvZone (List<ActionPlace> actionPlaces,ActionType actionType, int maxSamePlayerFamilyMember) {
		this.actionPlaces = actionPlaces;
		this.actionType = actionType;
		this.maxSamePlayerFamilyMember = maxSamePlayerFamilyMember;
	}

	public void addFamilyMember(FamilyMember familyMember, int index)
	{
		actionPlaces.get(index).addFamilyMember(familyMember);
	}


	public ActionType getActionType() {
		return actionType;
	}

	public boolean isAllowed(FamilyMember familyMember, int index)
	{
		if (familyMember.isNeutral())
		{
			return actionPlaces.get(index).isAllowed(familyMember);
		}

		int samePlayerFamilyMembers = 1;		//Check also the current
		for (ActionPlace actionPlace : actionPlaces)
		{
			for (FamilyMember familyMember1 : actionPlace.getMembers())
			{
				if (!familyMember1.isNeutral() && familyMember1.getPlayerUserName().equals(familyMember.getPlayerUserName()))
				{
					samePlayerFamilyMembers++;
				}
			}
		}

		if (samePlayerFamilyMembers > maxSamePlayerFamilyMember){
			return false;
		}

		//TODO malus su produzione se va in cella grande

		return actionPlaces.get(index).isAllowed(familyMember);
	}

	void removeFamilyMembers()
	{
		for (ActionPlace actionPlace : actionPlaces) {
			actionPlace.removeFamilyMembers();
		}
	}

	public List<Effect> getEffect(int index) {
		
         return Collections.unmodifiableList(this.actionPlaces.get(index).getEffects());
	}

	public List<ActionPlace> getActionPlaces() {
		return Collections.unmodifiableList(actionPlaces);
	}
}
