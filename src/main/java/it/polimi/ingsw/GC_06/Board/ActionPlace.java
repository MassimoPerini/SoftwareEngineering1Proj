package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;


import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Effect;

public abstract class ActionPlace {
	protected int maxFamiliari;
    protected ArrayList<Effect> effects;
	protected ArrayList<FamilyMember> members;
	protected int costo;
	
	public ActionPlace(ArrayList<Effect> effect, int costo) {
		this.maxFamiliari = 1;
		this.effects = effect;
		this.members = new ArrayList<>();
		this.costo = costo;
		
	}
	
	protected boolean isValidColor(FamilyMember member) {
		//TODO controllare che non ci sia un altro familiare dello stesso colore
		return true;
	}
	
	public abstract boolean isOccupied(FamilyMember member);

}
