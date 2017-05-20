package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.playerTools.Effect;

public class ActionPlaceNotTower extends ActionPlace {

	public ActionPlaceNotTower(ArrayList<Effect> effect, int costo) {
		super(effect, costo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isOccupied(FamilyMember member) {
		return true;
	}
	

}
