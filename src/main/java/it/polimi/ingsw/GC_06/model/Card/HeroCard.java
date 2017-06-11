package it.polimi.ingsw.GC_06.model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 17/05/17.
 */
public class HeroCard extends Card {


    private HashMap<String,Integer> cardConditions;
    private ResourceSet resourceConditions;


    public HeroCard(String path, HashMap<String, Integer> cardConditions, ResourceSet resourceConditions) {
        super(path);
        this.cardConditions = cardConditions;
        this.resourceConditions = resourceConditions;
    }

    public HashMap<String, Integer> getCardConditions() {
        return cardConditions;
    }

    public ResourceSet getResourceConditions() {
        return resourceConditions;
    }

    public boolean isActivable(Player player){
        if(resourceConditions.isIncluded(player.getResourceSet()) && player.getPlayerBoard().isIncluded(this.cardConditions)){
           return true;
        }
        return false;
    }
}
