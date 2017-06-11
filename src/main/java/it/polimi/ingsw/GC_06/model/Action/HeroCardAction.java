package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 6/11/17.
 */
public class HeroCardAction implements Action{

    private Player player;
    private HeroCard heroCard;


    @Override
    public void execute() {

    }

    @Override
    public boolean isAllowed() {
        return false;
    }
}
