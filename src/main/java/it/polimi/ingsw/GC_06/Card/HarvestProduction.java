package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Action.Action;
import it.polimi.ingsw.GC_06.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class HarvestProduction implements Action {

    private int point;
    private Player player;

    public HarvestProduction(int point, Player player) {
        this.point = point;
        this.player = player;
    }

    @Override
    public void execute() {

       ArrayList<DevelopmentCard> harvestCards =  player.getPlayerBoard().getGreenCard();

       for(DevelopmentCard harvestCard : harvestCards){
           harvestCard.getEffect().execute();
       }
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
