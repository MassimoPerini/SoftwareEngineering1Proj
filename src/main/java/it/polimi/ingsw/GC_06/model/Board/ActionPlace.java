package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author massimo
 * This class is the unlimited ActionPlace
 */

public class ActionPlace {
    private final List<Effect> effects;
	private List<FamilyMember> members;
	private int price;

	public ActionPlace(List<Effect> effect, int price ) {
		if (effect == null)
			throw new NullPointerException();
		this.effects = effect;
		this.members = new ArrayList<>();
		this.price = price;

	}

	//TODO N.B. Sarebbe meglio una delega???

	/**
	 *
	 * @param member il familiare che può essere associato allo spazio azione
	 * @return ritorna se il familiare può essere associato
	 * The only restriction is if the value of the familymember is lower than the field price
	 */
	public boolean isAllowed (FamilyMember member)
	{
		if (member == null)
			throw new NullPointerException();
		return member.getValue() >= price;
	}

	public void removeFamilyMembers()
	{
		this.members = new ArrayList<>();
	}

	/**
	 *
	 * @param familyMember il familiare che può essere associato allo spazio azione
	 * @return gli effetti legati allo spazio azione
	 * Adds the familymember to the ActionSpace if possible. Generate a IllegalArgumentException if it is not.
	 */
    public List<Effect> addFamilyMember(FamilyMember familyMember)
	{
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



	public int getPrice() {
		return price;
	}
}
