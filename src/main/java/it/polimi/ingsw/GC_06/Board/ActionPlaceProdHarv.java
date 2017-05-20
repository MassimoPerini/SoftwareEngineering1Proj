package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Effect;

public class ActionPlaceProdHarv extends ActionPlace {

	public ActionPlaceProdHarv(ArrayList<Effect> effect, int costo) {
		super(effect, costo);
	}

	@Override
	public boolean isOccupied(FamilyMember member) {
		//TODO da fare il controllo del colore in tutta la lista di familiari 
		return false;
	}
	

}
