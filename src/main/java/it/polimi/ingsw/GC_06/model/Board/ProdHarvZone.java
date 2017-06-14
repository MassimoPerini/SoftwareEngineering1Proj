package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class ProdHarvZone extends Observable{

	private final ArrayList <ActionPlace> actionPlaces;
	private int maxSamePlayerFamilyMember;

	public ProdHarvZone (ArrayList<ActionPlace> actionPlaces) {
		this.actionPlaces = actionPlaces;
	}

	public void addFamilyMember(FamilyMember familyMember, int index)
	{
		if (!isAllowed(familyMember, index))
			throw new IllegalStateException();
		actionPlaces.get(index).addFamilyMember(familyMember);
	}

	public boolean isAllowed(FamilyMember familyMember, int index)
	{
		int samePlayerFamilyMembers = 0;
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

		if (samePlayerFamilyMembers >= maxSamePlayerFamilyMember){
			return false;
		}

		//TODO malus su produzione se va in cella grande

		return actionPlaces.get(index).isAllowed(familyMember);
	}


	public List<Effect> getEffect(int index) {
		
         return Collections.unmodifiableList(this.actionPlaces.get(index).getEffects());
	}

	public List<ActionPlace> getActionPlaces() {
		return Collections.unmodifiableList(actionPlaces);
	}
}
