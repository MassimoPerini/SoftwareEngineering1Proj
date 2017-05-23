package it.polimi.ingsw.GC_06.playerTools;

public abstract class BonusMalusDecorator implements BonusMalus {
	protected BonusMalus decoratedBonusMalus;
	
	public BonusMalusDecorator(BonusMalus decoratedBonusMalus) {
		this.decoratedBonusMalus = decoratedBonusMalus;
	}

}
