package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

import java.util.ArrayList;
import java.util.List;


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

    DevelopmentCard getCard() {
        return card;
    }
    void setCard(DevelopmentCard c)
    {
        this.card = c;
    }

    public ActionPlace getActionPlace() {
        return actionPlace;
    }

    public List<Effect> addFamilyMember(FamilyMember familyMember) {
        if (!isAllowed(familyMember))
            throw new IllegalStateException();
        return actionPlace.addFamilyMember(familyMember);
    }

    List<Effect> getEffects ()
    {
        return this.actionPlace.getEffects();
    }

    public boolean isAllowed(FamilyMember familyMember) {
        return actionPlace.isAllowed(familyMember) && card != null;
    }


}
