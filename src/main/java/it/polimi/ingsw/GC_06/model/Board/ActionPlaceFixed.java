package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

import java.util.ArrayList;

public class ActionPlaceFixed implements ActionPlace{

	private int maxFamilyMembers;

	public ActionPlaceFixed(ArrayList<Effect> effect, int price, int maxFamilyMembers) {
		super(effect, price);
		this.maxFamilyMembers = maxFamilyMembers;
	//	maxFamilyMembers = Integer.parseInt(Setting.getInstance().getProperty("max_family_members"));		//Default = 1
	}

	@Override
	public ArrayList<Effect> addFamilyMember(FamilyMember familyMember) {
		return null;
	}

	@Override
	public boolean isAllowed(FamilyMember member)
	{
		return super.isAllowed(member) && this.getMembers().size() < maxFamilyMembers;
	}

	@Override
	public ArrayList<Effect> getEffects() {
		return null;
	}

}


