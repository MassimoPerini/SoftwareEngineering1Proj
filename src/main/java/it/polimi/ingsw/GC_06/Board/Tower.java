package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;
/**
 * Created by massimo on 13/05/17.
 */
public class Tower {

    private ArrayList<TowerFloor> floors = new ArrayList<>();

    public Tower( ArrayList<TowerFloor> floors){
    	this.floors = floors;
    }
}
