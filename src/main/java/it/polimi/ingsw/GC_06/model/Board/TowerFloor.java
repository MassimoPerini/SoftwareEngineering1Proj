package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

import java.util.ArrayList;


/**
 * Created by massimo on 12/05/17.
 */
public class TowerFloor {

    private DevelopmentCard card;       //TODO Lista????
    private SmallActionPlace smallActionPlace;


    /*
    public TowerFloor(ArrayList<Effect> effect, int price, DevelopmentCard card )
    {
        this.smallActionPlace = new ActionPlaceFixed(effect , price);
        this.card = card;
    }*/

    public TowerFloor(SmallActionPlace smallActionPlace, DevelopmentCard card)
    {
        this.smallActionPlace = smallActionPlace;
        this.card = card;
    }

    DevelopmentCard getCard() {
        return card;
    }
    void setCard(DevelopmentCard c)
    {
        this.card = c;
    }

    public SmallActionPlace getSmallActionPlace() {
        return smallActionPlace;
    }

    public ArrayList<Effect> addFamilyMember(FamilyMember familyMember) {
        if (!isAllowed(familyMember))
            throw new IllegalStateException();
        return smallActionPlace.addFamilyMember(familyMember);
    }

    ArrayList<Effect> getEffects ()
    {
        return this.smallActionPlace.getEffects();
    }

    public boolean isAllowed(FamilyMember familyMember) {
        return smallActionPlace.isAllowed(familyMember) && card != null;
    }


}
