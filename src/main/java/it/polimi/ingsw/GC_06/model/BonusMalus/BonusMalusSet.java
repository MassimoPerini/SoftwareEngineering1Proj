package it.polimi.ingsw.GC_06.model.BonusMalus;


import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType.*;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusSet {


    private  HashMap<BonusMalusType,ArrayList<BonusMalusOnResources>> bonusMalusOnResources = new HashMap<>();
    private HashMap<BonusMalusType,ArrayList<BonusMalusOnAction>> bonusMalusOnAction= new HashMap<>();
    private HashMap<BonusMalusType,ArrayList<BonusMalusOnSettings>> bonusMalusOnSetting = new HashMap<>();
    private HashMap<BonusMalusType,ArrayList<BonusMalusOnAccess>> bonusMalusOnAccess= new HashMap<>();
    private HashMap<BonusMalusType,ArrayList<BonusMalusOnCost>> bonusMalusOnCost= new HashMap<>();
    private HashMap<BonusMalusType,ArrayList<BonusMalusOnEnd>> bonusMalusOnEnd= new HashMap<>();



    public BonusMalusSet() {
        this.bonusMalusOnResources.put(BONUSMALUSONRESOURCE,new ArrayList<>());
        this.bonusMalusOnAction .put(BONUSMALUSONACTION,new ArrayList<>());
        this.bonusMalusOnSetting.put(BONUSMALUSONSETTING,new ArrayList<>());
        this.bonusMalusOnCost.put(BONUSMALUSONCOST,new ArrayList<>());
        this.bonusMalusOnAccess.put(BONUSMALUSONACCESS,new ArrayList<>());
        this.bonusMalusOnEnd.put(BONUSMALUSONEND,new ArrayList<>());
    }

    public void joinSet(BonusMalusSet bonusMalusSet){
        this.addActionBonusMalus(bonusMalusSet.bonusMalusOnAction.get(BONUSMALUSONACTION));
        this.addResourceBonusMalus(bonusMalusSet.bonusMalusOnResources.get(BONUSMALUSONRESOURCE));
        this.addEndBonusMalus(bonusMalusSet.bonusMalusOnEnd.get(BONUSMALUSONEND));
        this.addAccessBonusMalus(bonusMalusSet.bonusMalusOnAccess.get(BONUSMALUSONACCESS));
        this.addCostBonusMalus(bonusMalusSet.bonusMalusOnCost.get(BONUSMALUSONCOST));
        this.addBonusMalusOnSettings(bonusMalusSet.bonusMalusOnSetting.get(BONUSMALUSONSETTING));

    }

    public void addActionBonusMalus(List<BonusMalusOnAction> bonusMalusOnActions){
        Set<BonusMalusType> keySet = this.bonusMalusOnAction.keySet();
        for(BonusMalusType bonusMaluskey : keySet){
            this.bonusMalusOnAction.get(bonusMaluskey).addAll(bonusMalusOnActions);
        }
    }

    public void addResourceBonusMalus(List<BonusMalusOnResources> bonusMalusOnResources){


            for (BonusMalusType bonusMalusKey : this.bonusMalusOnResources.keySet()) {
                this.bonusMalusOnResources.get(bonusMalusKey).addAll(bonusMalusOnResources);
        }

    }

    public void addEndBonusMalus(List<BonusMalusOnEnd>  bonusMalusOnEnd){
        if(bonusMalusOnEnd.size()!= 0) {
            for (BonusMalusType bonusMalusKey : this.bonusMalusOnEnd.keySet()) {
                this.bonusMalusOnEnd.get(bonusMalusKey).addAll(bonusMalusOnEnd);
            }
        }
    }

    public void addBonusMalusOnSettings(List<BonusMalusOnSettings> bonusMalusOnSettings){
        if(bonusMalusOnSettings.size() != 0){
            for(BonusMalusType bonusMalusOnSettings1 :this.bonusMalusOnSetting.keySet()){
                this.bonusMalusOnSetting.get(bonusMalusOnSettings1).addAll(bonusMalusOnSettings);
            }
        }
    }
    public void addCostBonusMalus(List<BonusMalusOnCost> bonusMalusOnCost){
        if(bonusMalusOnCost.size()!= 0) {
            for (BonusMalusType bonusMalusKey : this.bonusMalusOnCost.keySet()) {
                this.bonusMalusOnCost.get(bonusMalusKey).addAll(bonusMalusOnCost);
            }
        }
    }
    public void addAccessBonusMalus(List<BonusMalusOnAccess> bonusMalusOnAccess){
        if(bonusMalusOnAccess.size()!= 0) {
            for (BonusMalusType bonusMalusKey : this.bonusMalusOnCost.keySet()) {
                this.bonusMalusOnAccess.get(bonusMalusKey).addAll(bonusMalusOnAccess);
            }
        }
    }
    public int removeBonusMalusAction(ArrayList<BonusMalusOnAction> actions, int i){

        if(!actions.get(i).isPermanent()){
            actions.remove(i);
            return  --i;
        }
        return i;
    }



    public int removeBonusMalusResources(ArrayList<BonusMalusOnResources> resources, int i){

        if(!resources.get(i).isPermanent()){
            resources.remove(i);
            return  --i;
        }
        return i;

    }

    public int removeBonusMalusAccess(ArrayList<BonusMalusOnAccess> accesses, int i){
        if(!accesses.get(i).isPermanent()){
            accesses.remove(i);
            return  --i;
        }
        return i;

    }

    public int removeBonusMalusEnd(ArrayList<BonusMalusOnEnd> ends, int i){

        if(!ends.get(i).isPermanent()){
            ends.remove(i);
            return --i;
        }
        return i;

    }

    public int removeBonusMalusCost(ArrayList<BonusMalusOnCost> bonusMalusOnCosts,int i){
        if(!bonusMalusOnCosts.get(i).isPermanent()){
            bonusMalusOnCosts.remove(i);

            return --i;
        }
        return i;
    }

    public int removeBonusMalusSetting(ArrayList<BonusMalusOnSettings> settings, int i){
        if(!settings.get(i).isPermanent()){
            settings.remove(i);
            return --i;
        }
        return i;
    }


    public HashMap<BonusMalusType, ArrayList<BonusMalusOnResources>> getBonusMalusOnResources() {
        return bonusMalusOnResources;
    }

    public HashMap<BonusMalusType, ArrayList<BonusMalusOnAction>> getBonusMalusOnAction() {
        return bonusMalusOnAction;
    }

    public HashMap<BonusMalusType, ArrayList<BonusMalusOnSettings>> getBonusMalusOnSetting() {
        return bonusMalusOnSetting;
    }

    public HashMap<BonusMalusType, ArrayList<BonusMalusOnAccess>> getBonusMalusOnAccess() {
        return bonusMalusOnAccess;
    }

    public HashMap<BonusMalusType, ArrayList<BonusMalusOnCost>> getBonusMalusOnCost() {
        return bonusMalusOnCost;
    }

    public HashMap<BonusMalusType, ArrayList<BonusMalusOnEnd>> getBonusMalusOnEnd() {
        return bonusMalusOnEnd;
    }
}


