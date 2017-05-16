package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.FamilyMember;

/**
 * Created by massimo on 12/05/17.
 */
public class TowerFloor {

    private DevelopmentCard card;
    private ActionPlace actionPlace;

    public TowerFloor(int requiredValue)
    {
        this.actionPlace = new SingleActionPlace(requiredValue);
    }

    public DevelopmentCard addFamilyMember(FamilyMember member) {
        actionPlace.addFamilyMember(member);
        return card;
    }
}
