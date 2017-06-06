package it.polimi.ingsw.GC_06.model.Board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Effect.Effect;

/**
 * @author massimo
 * This class is the unlimited ActionPlace
 * We HAVE TO use the delegation pattern instead of the extension
 */

public class ActionPlace {
    private final List<Effect> effects;
	private final ArrayList<FamilyMember> members;
	private int price;
	
	public ActionPlace(List<Effect> effect, int price) {
		if (effect == null)
			throw new NullPointerException();
		this.effects = effect;
		this.members = new ArrayList<>();
		this.price = price;
		
	}

	//TODO N.B. Sarebbe meglio una delega???

	/**
	 *
	 * @param member
	 * @return
	 * The only restriction is if the value of the familymember is lower than the field price
	 */
	public boolean isAllowed (FamilyMember member)
	{
		if (member == null)
			throw new NullPointerException();
		return member.getValue() >= price;
	}

	/**
	 *
	 * @param familyMember
	 * @return
	 * Adds the familymember to the ActionSpace if possible. Generate a IllegalArgumentException if it is not.
	 */
    public List<Effect> addFamilyMember(FamilyMember familyMember)
	{
		if (!isAllowed(familyMember))
			throw new IllegalArgumentException();
		this.members.add(familyMember);
		return effects;
	}

	/*
	The getters should be used in read-only mode
	 */
	public List<FamilyMember> getMembers() {
		return Collections.unmodifiableList(members);
	}

	public List<Effect> getEffects() {
		return Collections.unmodifiableList(effects);
	}
}
