package it.polimi.ingsw.GC_06.model.BonusMalus;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusSet {


    private  HashMap<String,ArrayList<BonusMalusOnResources>> bonusMalusOnResources = new HashMap<>();
    private HashMap<String,ArrayList<BonusMalusOnAction>> bonusMalusOnAction= new HashMap<>();
    private HashMap<String,ArrayList<BonusMalusOnEnd>> bonusMalusOnEnd= new HashMap<>();
    private HashMap<String,ArrayList<BonusMalusOnAccess>> bonusMalusOnAccess= new HashMap<>();
    private HashMap<String,ArrayList<BonusMalusOnCost>> bonusMalusOnCost= new HashMap<>();



    public BonusMalusSet() {
        this.bonusMalusOnResources.put("BONUSMALUSONRESOURCE",new ArrayList<>());
        this.bonusMalusOnAction .put("BONUSMALUSONACTION",new ArrayList<>());
        this.bonusMalusOnEnd.put("BONUSMALUSONEND",new ArrayList<>());
        this.bonusMalusOnCost.put("BONUSMALUSONCOST",new ArrayList<>());
        this.bonusMalusOnAccess.put("BONUSMALUSONACCESS",new ArrayList<>());
    }

    public HashMap<String, ArrayList<BonusMalusOnResources>> getBonusMalusOnResources() {
        return bonusMalusOnResources;
    }

    public HashMap<String, ArrayList<BonusMalusOnAction>> getBonusMalusOnAction() {
        return bonusMalusOnAction;
    }

    public HashMap<String, ArrayList<BonusMalusOnCost>> getBonusMalusOnCost() {
        return bonusMalusOnCost;
    }

    public HashMap<String, ArrayList<BonusMalusOnEnd>> getBonusMalusOnEnd() {
        return bonusMalusOnEnd;
    }


    public void joinSet(BonusMalusSet bonusMalusSet){
        this.addActionBonusMalus(bonusMalusSet.bonusMalusOnAction.get("BONUSMALUSONACTION"));
        this.addResourceBonusMalus(bonusMalusSet.bonusMalusOnResources.get("BONUSMALUSONRESOURCE"));
        this.addEndBonusMalus(bonusMalusSet.bonusMalusOnEnd.get("BONUSMALUSONEND"));
        this.addAccessBonusMalus(bonusMalusSet.bonusMalusOnAccess.get("BONUSMALUSONACCESS"));
        this.addCostBonusMalus(bonusMalusSet.bonusMalusOnCost.get("BONUSMALUSONCOST"));

    }

    public void addActionBonusMalus(List<BonusMalusOnAction> bonusMalusOnActions){
        Set<String> keySet = this.bonusMalusOnAction.keySet();
        for(String bonusMaluskey : keySet){
            this.bonusMalusOnAction.get(bonusMaluskey).addAll(bonusMalusOnActions);
        }
    }
    public void addResourceBonusMalus(List<BonusMalusOnResources> bonusMalusOnResources){


            for (String bonusMalusKey : this.bonusMalusOnResources.keySet()) {
                this.bonusMalusOnResources.get(bonusMalusKey).addAll(bonusMalusOnResources);
        }

    }
    public void addEndBonusMalus(List<BonusMalusOnEnd>  bonusMalusOnEnd){
        if(bonusMalusOnEnd.size()!= 0) {
            for (String bonusMalusKey : this.bonusMalusOnEnd.keySet()) {
                this.bonusMalusOnEnd.get(bonusMalusKey).addAll(bonusMalusOnEnd);
            }
        }
    }
    public void addCostBonusMalus(List<BonusMalusOnCost> bonusMalusOnCost){
        if(bonusMalusOnCost.size()!= 0) {
            for (String bonusMalusKey : this.bonusMalusOnCost.keySet()) {
                this.bonusMalusOnCost.get(bonusMalusKey).addAll(bonusMalusOnCost);
            }
        }
    }
    public void addAccessBonusMalus(List<BonusMalusOnAccess> bonusMalusOnAccess){
        if(bonusMalusOnAccess.size()!= 0) {
            for (String bonusMalusKey : this.bonusMalusOnCost.keySet()) {
                this.bonusMalusOnAccess.get(bonusMalusKey).addAll(bonusMalusOnAccess);
            }
        }
    }

    public HashMap<String, ArrayList<BonusMalusOnAccess>> getBonusMalusOnAccess() {
        return bonusMalusOnAccess;
    }



    public int removeBonusMalusAction(ArrayList<BonusMalusOnAction> actions, int i){

        if(!actions.get(i).isPermanent()){
            actions.remove(i);
            return  i--;
        }
        return i;
    }

    public int removeBonusMalusResources(ArrayList<BonusMalusOnResources> resources, int i){

        if(!resources.get(i).isPermanent()){
            resources.remove(i);
            return  i--;
        }
        return i;

    }

    public int removeBonusMalusAccess(ArrayList<BonusMalusOnAccess> accesses, int i){
        if(!accesses.get(i).isPermanent()){
            accesses.remove(i);
            return  i--;
        }
        return i;

    }

    public int removeBonusMalusEnd(ArrayList<BonusMalusOnEnd> ends, int i){

        if(!ends.get(i).isPermanent()){
            ends.remove(i);
            return i--;
        }
        return i;

    }

    public int removeBonusMalusCost(ArrayList<BonusMalusOnCost> bonusMalusOnCosts,int i){
        if(!bonusMalusOnCosts.get(i).isPermanent()){
            bonusMalusOnCosts.remove(i);
            i = i-1;
            return i;
        }
        return i;
    }

}


