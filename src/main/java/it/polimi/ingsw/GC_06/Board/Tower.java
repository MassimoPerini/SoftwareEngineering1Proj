package it.polimi.ingsw.GC_06.Board;

import java.util.ArrayList;

/**
 * Created by massimo on 13/05/17.
 */
public class Tower {

    private ArrayList<TowerFloor> floors = new ArrayList<>();

    public Tower()
    {
        this(4);
    }

    public Tower(int nFloors)
    {
        for (int i=0;i<nFloors;i++)
            floors.add(new TowerFloor(1+i));
    }

}
