package it.polimi.ingsw.GC_06.model.BonusMalus;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusSet {

    /** secondome va un po' ripensato*/
    private  HashMap<String,ArrayList<BonusMalusOnResources>> bonusMalusOnResources;
    private HashMap<String,ArrayList<BonusMalusOnAction>> bonusMalusOnAction;
    private HashMap<String,ArrayList<BonusMalusOnCost>> bonusMalusOnConditions;
    private HashMap<String,ArrayList<BonusMalusOnEnd>> bonusMalusOnEnd;
    private HashMap<String,ArrayList<BonusMalusOnAccess>> bonusMalusOnAccess;
    private HashMap<String,ArrayList<BonusMalusOnCost>> bonusMalusOnCost;

    public BonusMalusSet() {
        this.bonusMalusOnResources = new HashMap<String,ArrayList<BonusMalusOnResources>>();
        this.bonusMalusOnAction = new HashMap<String,ArrayList<BonusMalusOnAction>>();
        this.bonusMalusOnConditions = new HashMap<String,ArrayList<BonusMalusOnCost>>();
        this.bonusMalusOnEnd = new HashMap<>();
        this.bonusMalusOnCost = new HashMap<>();
    }

    public HashMap<String, ArrayList<BonusMalusOnResources>> getBonusMalusOnResources() {
        return bonusMalusOnResources;
    }

    public HashMap<String, ArrayList<BonusMalusOnAction>> getBonusMalusOnAction() {
        return bonusMalusOnAction;
    }

    public HashMap<String, ArrayList<BonusMalusOnCost>> getBonusMalusOnCost() {
        return bonusMalusOnConditions;
    }

    public HashMap<String, ArrayList<BonusMalusOnCost>> getBonusMalusOnConditions() {
        return bonusMalusOnConditions;
    }

    public HashMap<String, ArrayList<BonusMalusOnEnd>> getBonusMalusOnEnd() {
        return bonusMalusOnEnd;
    }

    public void joinSet(BonusMalusSet bonusMalusSet){
        this.add(bonusMalusSet.bonusMalusOnAction);
        this.add1(bonusMalusSet.bonusMalusOnResources);
        this.add2(bonusMalusSet.bonusMalusOnEnd);
        //this.add3(bonusMalusSet.bonusMalusOnConditions);
        this.add4(bonusMalusSet.bonusMalusOnAccess);
    }

    private void add(HashMap<String,ArrayList<BonusMalusOnAction>> bonusMalusOnAction){
        this.bonusMalusOnAction.putAll(bonusMalusOnAction);
    }
    private void add1(HashMap<String,ArrayList<BonusMalusOnResources>> bonusMalusOnResources){
        this.bonusMalusOnResources.putAll(bonusMalusOnResources);
    }
    private void add2(HashMap<String,ArrayList<BonusMalusOnEnd>> bonusMalusOnEnd){
        this.bonusMalusOnEnd.putAll(bonusMalusOnEnd);
    }
    /**private void add3(HashMap<String,ArrayList<BonusMalusOnCost>> bonusMalusOnCost){
        this.bonusMalusOnCost().putAll(bonusMalusOnCost);
    }*/
    private void add4(HashMap<String,ArrayList<BonusMalusOnAccess>> bonusMalusOnAccess){
        this.bonusMalusOnAccess.putAll(bonusMalusOnAccess);
    }

    public HashMap<String, ArrayList<BonusMalusOnAccess>> getBonusMalusOnAccess() {
        return bonusMalusOnAccess;
    }

    private void resetBonusMalus(){

        for(BonusMalusOnAccess bonusMalus : this.bonusMalusOnAccess.get("ACCESS")){
            bonusMalus.setON(true);
        }

        for(BonusMalusOnEnd bonusMalus : this.bonusMalusOnEnd.get("ENDMALUS")){
            bonusMalus.setON(true);
        }
        for(BonusMalusOnResources bonusMalusOnResource : this.bonusMalusOnResources.get("RESOURCEMALUS")){
            bonusMalusOnResource.setON(true);
        }
        for(BonusMalusOnAction bonusMalusOnAction : this.bonusMalusOnAction.get("ACTIONBONUSMALUS")){
            bonusMalusOnAction.setON(true);
        }

        for(BonusMalusOnCost bonusMalusOnCost : this.bonusMalusOnCost.get("COSTBONUSMALUS")){
            bonusMalusOnCost.setON(true);
        }
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

}


