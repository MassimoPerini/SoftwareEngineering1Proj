package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class ProdHarvZone implements Component{

	private ArrayList <SmallActionPlace> smallActionPlaces;
	private int maxSamePlayerFamilyMember;
	
	public ProdHarvZone (ArrayList<SmallActionPlace> smallActionPlaces) {
		this.smallActionPlaces = smallActionPlaces;
	}

	@Override
	public void addFamilyMember(FamilyMember familyMember, int index)
	{
		if (!isAllowed(familyMember, index))
			throw new IllegalStateException();
		smallActionPlaces.get(index).addFamilyMember(familyMember);
	}

	@Override
	public boolean isAllowed(FamilyMember familyMember, int index)
	{
		int samePlayerFamilyMembers = 0;
		for (SmallActionPlace smallActionPlace : smallActionPlaces)
		{
			for (FamilyMember familyMember1 : smallActionPlace.getMembers())
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

		return smallActionPlaces.get(index).isAllowed(familyMember);
	}


	@Override
	public ArrayList<Effect> getEffect(int index) {
		
         return this.smallActionPlaces.get(index).getEffects();
	}
}
