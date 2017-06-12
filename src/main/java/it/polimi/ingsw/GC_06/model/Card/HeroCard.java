package it.polimi.ingsw.GC_06.model.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.BonusMalus.SpecialBonusMalus;
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
    private BonusMalusSet bonusMalusSet;
    private boolean permanent,ON;
    private ArrayList<BonusMalusOnAction> bonusMalusOnActions;
    private ArrayList <SpecialBonusMalus> specialBonusMaluses;



    public HeroCard(String path, HashMap<String, Integer> cardConditions, ResourceSet resourceConditions, ArrayList<Effect> effectList,boolean permanent,
                    BonusMalusSet bonusMaluSet) {
        super(path);
        this.cardConditions = cardConditions;
        this.resourceConditions = resourceConditions;
        this.effectList = effectList;
        this.permanent = permanent;
        this.ON = true;
        this.bonusMalusSet = bonusMaluSet;
    }

    public HashMap<String, Integer> getCardConditions() {
        return cardConditions;
    }

    public ResourceSet getResourceConditions() {
        return resourceConditions;
    }

    public ArrayList<Effect> getEffectList() {
        return effectList;
    }

    public boolean isActivable(Player player){
        if(this.ON = true & resourceConditions.isIncluded(player.getResourceSet()) && player.getPlayerBoard().isIncluded(this.cardConditions)){
           return true;
        }
        return false;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public BonusMalusSet getBonusMalusSet() {
        return bonusMalusSet;
    }

    public void setON(boolean ON) {
        this.ON = ON;
    }
}
