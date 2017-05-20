package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.playerTools.Effect;

import java.util.ArrayList;

import it.polimi.ingsw.GC_06.FamilyMember;

/**
 * Created by massimo on 12/05/17.
 */
public class TowerFloor {

    private DevelopmentCard card;
    private ActionPlace actionPlace;

    public TowerFloor(ArrayList<Effect> effect, int costo, DevelopmentCard card )
    {
        this.actionPlace = new ActionPlaceTower(effect , costo);
        this.card = card;
    }

}
