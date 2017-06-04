package it.polimi.ingsw.GC_06.model.BonusMalus;

import it.polimi.ingsw.GC_06.model.Action.PlayType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by giuseppe on 6/2/17.
 */
public class BonusMalusSet {

    /** secondome va un po' ripensato*/
    HashMap<PlayType,ArrayList<BonusMalusOnResources>> bonusMalusOnResources;
    HashMap<PlayType,ArrayList<BonusMalusOnAction>> bonusMalusOnAction;

    public BonusMalusSet() {
        this.bonusMalusOnResources = new HashMap<PlayType,ArrayList<BonusMalusOnResources>>();
        this.bonusMalusOnAction = new HashMap<PlayType,ArrayList<BonusMalusOnAction>>();
    }

    public HashMap<PlayType, ArrayList<BonusMalusOnResources>> getBonusMalusOnResources() {
        return bonusMalusOnResources;
    }

    public HashMap<PlayType, ArrayList<BonusMalusOnAction>> getBonusMalusOnAction() {
        return bonusMalusOnAction;
    }
}
