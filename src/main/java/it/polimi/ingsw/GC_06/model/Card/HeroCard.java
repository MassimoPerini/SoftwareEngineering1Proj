package it.polimi.ingsw.GC_06.model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 17/05/17.
 * la classe rappresenta una carta eroe
 */
public class HeroCard extends Card {


    private HashMap<String,Integer> cardConditions;
    private ResourceSet resourceConditions;
    private List<Effect> effectList;
    private String cardType;
    private boolean cardStatus;
    private boolean permanent;



    public HeroCard(String path, HashMap<String, Integer> cardConditions, ResourceSet resourceConditions, List<Effect> effectList,
                    boolean permanent,String cardType) {
        super(path);
        this.cardConditions = cardConditions;
        this.resourceConditions = resourceConditions;
        this.effectList = effectList;
        this.cardStatus = true;
        this.cardType = cardType;
        this.permanent = permanent;
    }



    public boolean isActivable(Player player){

        if(this.cardStatus = true && player.getResourceSet().isIncluded(resourceConditions)
                && player.getPlayerBoard().isIncluded(this.cardConditions)){
            return true;
        }
        return false;
    }

    public void setCardStatus(boolean cardStatus) {
        this.cardStatus = cardStatus;
    }

    public List<Effect> getEffectList() {
        return effectList;
    }

    public String getCardType() {
        return cardType;
    }

    public boolean isCardStatus() {
        return cardStatus;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public ResourceSet getResourceConditions() {
        return resourceConditions;
    }

    public HashMap<String, Integer> getCardConditions() {
        return cardConditions;
    }


}
