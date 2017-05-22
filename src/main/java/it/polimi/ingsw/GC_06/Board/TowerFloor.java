package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Action.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;

import java.util.ArrayList;


/**
 * Created by massimo on 12/05/17.
 */
public class TowerFloor {

    private DevelopmentCard card;       //TODO Lista????
    private ActionPlace actionPlace;


    /*
    public TowerFloor(ArrayList<Effect> effect, int price, DevelopmentCard card )
    {
        this.actionPlace = new ActionPlaceFixed(effect , price);    
        this.card = card;
    }*/

    public TowerFloor(ActionPlace actionPlace, DevelopmentCard card)
    {
        this.actionPlace = actionPlace;
        this.card = card;
    }

    public ActionPlace getActionPlace() {
        return actionPlace;
    }

    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember) {
        if (!isAllowed(familyMember))
            throw new IllegalStateException();
        return actionPlace.addFamilyMember(familyMember);
    }

    public boolean isAllowed(FamilyMember familyMember) {
        return actionPlace.isAllowed(familyMember) && card != null;
    }


}
