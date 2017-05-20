package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Action.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class ProdHarvZone implements Component{

	private ArrayList <ActionPlace> actionPlaces;
	
	public ProdHarvZone () {
		this.actionPlaces = new ArrayList<>();
	}

	public void addActionPlace (ActionPlace actionPlace)
	{
		this.actionPlaces.add(actionPlace);
	}


	@Override
	public ArrayList<Effect> addFamilyMember(FamilyMember familyMember, int index) {
		return actionPlaces.get(index).addFamilyMember(familyMember);
	}

	@Override
	public boolean isAllowed(FamilyMember familyMember, int index) {
		return actionPlaces.get(index).isAllowed(familyMember);
	}
}
