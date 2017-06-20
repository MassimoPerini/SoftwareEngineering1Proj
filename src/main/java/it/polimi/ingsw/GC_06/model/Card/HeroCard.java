package it.polimi.ingsw.GC_06.model.Card;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 17/05/17.
 */
public class HeroCard extends Card {


    private HashMap<String,Integer> cardConditions;
    private ResourceSet resourceConditions;
    private ArrayList<Effect> effectList;
    private String cardType;
    private boolean CardStatus;
    private boolean permanent;



    public HeroCard(String path, HashMap<String, Integer> cardConditions, ResourceSet resourceConditions, ArrayList<Effect> effectList,
                    boolean permanent,String cardType) {
        super(path);
        this.cardConditions = cardConditions;
        this.resourceConditions = resourceConditions;
        this.effectList = effectList;
        this.CardStatus = true;
        this.cardType = cardType;
    }



    public boolean isActivable(Player player){
        if(this.CardStatus = true & resourceConditions.isIncluded(player.getResourceSet()) && player.getPlayerBoard().isIncluded(this.cardConditions)){
           return true;
        }
        return false;
    }

    public void setCardStatus(boolean cardStatus) {
        this.CardStatus = cardStatus;
    }

    public ArrayList<Effect> getEffectList() {
        return effectList;
    }

    public String getCardType() {
        return cardType;
    }

    public ResourceSet getResourceConditions() {
        return resourceConditions;
    }

    public HashMap<String, Integer> getCardConditions() {
        return cardConditions;
    }
}
