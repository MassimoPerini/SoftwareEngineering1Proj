package it.polimi.ingsw.GC_06.model.BonusMalus;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusSet {

    /** secondome va un po' ripensato*/
    HashMap<String,ArrayList<BonusMalusOnResources>> bonusMalusOnResources;
    HashMap<String,ArrayList<BonusMalusOnAction>> bonusMalusOnAction;
    HashMap<String,ArrayList<BonusMalusOnCost>> bonusMalusOnConditions;
    HashMap<String,ArrayList<BonusMalusOnEnd>> bonusMalusOnEnd;
    public BonusMalusSet() {
        this.bonusMalusOnResources = new HashMap<String,ArrayList<BonusMalusOnResources>>();
        this.bonusMalusOnAction = new HashMap<String,ArrayList<BonusMalusOnAction>>();
        this.bonusMalusOnConditions = new HashMap<String,ArrayList<BonusMalusOnCost>>();
        this.bonusMalusOnEnd = new HashMap<>();
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
        this.add3(bonusMalusSet.bonusMalusOnConditions);
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
    private void add3(HashMap<String,ArrayList<BonusMalusOnCost>> bonusMalusOnCost){
        this.getBonusMalusOnCost().putAll(bonusMalusOnCost);
    }

}


