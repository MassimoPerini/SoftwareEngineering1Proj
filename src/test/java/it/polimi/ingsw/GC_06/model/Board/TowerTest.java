package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import org.junit.Before;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giuseppe on 6/23/17.
 */
public class TowerTest {

    private ActionPlace actionPlace;
    private TowerFloor towerFloor;
    private TowerFloor towerFloor1;
    private TowerFloor towerFloor2;
    private TowerFloor towerFloor3;
    private Tower tower;
    private DevelopmentCard developmentCard;
    private List<TowerFloor> towerFloors = new LinkedList<>();

    @Before
    public void setUp(){
        developmentCard = new DevelopmentCard("Peppe",1,new LinkedList<Requirement>(),new LinkedList<Effect>(),"YELLOW",new HashMap<>());
        actionPlace = new ActionPlace(new LinkedList<Effect>(),2);
        towerFloor = new TowerFloor(actionPlace,developmentCard);
        towerFloor1 = new TowerFloor(actionPlace,developmentCard);
        towerFloor2 = new TowerFloor(actionPlace,developmentCard);
        towerFloor3  = new TowerFloor(actionPlace,developmentCard);
    }

}
