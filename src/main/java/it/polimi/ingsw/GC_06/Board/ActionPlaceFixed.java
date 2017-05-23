package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;

import java.util.ArrayList;

public class ActionPlaceFixed extends ActionPlace {

	private int maxFamilyMembers;

	public ActionPlaceFixed(ArrayList<Effect> effect, int price, int maxFamilyMembers) {
		super(effect, price);
		this.maxFamilyMembers = maxFamilyMembers;
	//	maxFamilyMembers = Integer.parseInt(Setting.getInstance().getProperty("max_family_members"));		//Default = 1
	}

	@Override
	public boolean isAllowed(FamilyMember member)
	{
		return super.isAllowed(member) && this.getMembers().size() < maxFamilyMembers;
	}

}