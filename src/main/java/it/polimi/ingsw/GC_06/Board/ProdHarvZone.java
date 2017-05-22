package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class ProdHarvZone implements Component{

	private ArrayList <ActionPlace> actionPlaces;
	private int maxSamePlayerFamilyMember;
	
	public ProdHarvZone (ArrayList<ActionPlace> actionPlaces) {
		this.actionPlaces = actionPlaces;
	}

	@Override
	public ArrayList<Effect> addFamilyMember(FamilyMember familyMember, int index)
	{
		if (!isAllowed(familyMember, index))
			throw new IllegalStateException();
		return actionPlaces.get(index).addFamilyMember(familyMember);
	}

	@Override
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
}
