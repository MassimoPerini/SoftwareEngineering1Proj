package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.ArrayList;

/**
 * @author massimo
 * This class is the fixed-element ActionPlace
 * We HAVE TO use the delegation pattern instead of the extension
 */
public class ActionPlaceFixed extends ActionPlace {

	private int maxFamilyMembers;

	public ActionPlaceFixed(ArrayList<Effect> effect, int price, int maxFamilyMembers) {
		super(effect, price);
		this.maxFamilyMembers = maxFamilyMembers;
	//	maxFamilyMembers = Integer.parseInt(Setting.getInstance().getProperty("max_family_members"));		//Default = 1
	}

	/**
	 * Inherited check (the value of the family member) + check if the number of familyMembers is lower than the maximum
	 * (if it is equals I readched the treshold)
	 * @param member
	 * @return
	 */
	@Override
	public boolean isAllowed(FamilyMember member)
	{
		return super.isAllowed(member) && this.getMembers().size() < maxFamilyMembers;
	}

}
