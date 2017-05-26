package it.polimi.ingsw.GC_06.model.playerTools;

public abstract class BonusMalusDecorator implements BonusMalus {
	protected BonusMalus decoratedBonusMalus;
	
	public BonusMalusDecorator(BonusMalus decoratedBonusMalus) {
		this.decoratedBonusMalus = decoratedBonusMalus;
	}

}
