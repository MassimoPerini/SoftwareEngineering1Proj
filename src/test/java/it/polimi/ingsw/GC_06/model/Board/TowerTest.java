package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

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
    private FamilyMember familyMember;


    @Before
    public void setUp(){
        familyMember = new FamilyMember("green","peppe");
        familyMember.setValue(15);
        developmentCard = new DevelopmentCard("Peppe",1,new LinkedList<Requirement>(),new LinkedList<Effect>(),"YELLOW",new HashMap<>());
        actionPlace = new ActionPlace(new LinkedList<Effect>(),2);
        actionPlace.addFamilyMember(familyMember);
        towerFloor = new TowerFloor(actionPlace,developmentCard);
        towerFloor1 = new TowerFloor(actionPlace,developmentCard);
        towerFloor2 = new TowerFloor(actionPlace,developmentCard);
        towerFloor3  = new TowerFloor(actionPlace,developmentCard);
        towerFloors.add(towerFloor);
        towerFloors.add(towerFloor1);
        towerFloors.add(towerFloor2);
        towerFloors.add(towerFloor3);
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY,-3);
        tower = new Tower(towerFloors,2,1,"yellow",resourceSet);
    }

    @Test
    public void firstTest(){

        tower.addFamilyMember(familyMember, 2);
        assertTrue(tower.getTowerFloor().get(2).getActionPlace().getMembers().size() == 2);
    }

    @Test
    public void secondTest(){

        tower.addFamilyMember(familyMember, 2);
        tower.addFamilyMember(familyMember,1);
        tower.removeFamilyMembers();
        assertTrue(tower.getTowerFloor().get(2).getActionPlace().getMembers().size() == 0);
        assertTrue(tower.getTowerFloor().get(1).getActionPlace().getMembers().size() == 0);

    }

    @Test
    public void thirdTest(){
        developmentCard = tower.pickCard(1);
        assertTrue(developmentCard.getIdColour().equals("YELLOW"));
    }

    @Test
    public void fourthTest(){
        assertFalse(tower.isAllowed(familyMember,1));
    }

    @Test
    public void throwPenalityTest(){

        assertTrue(tower.shouldThrowPenality("peppe"));
    }
}
