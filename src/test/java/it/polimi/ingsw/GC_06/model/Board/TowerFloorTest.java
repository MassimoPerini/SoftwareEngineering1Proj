package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by giuseppe on 6/23/17.
 */
public class TowerFloorTest {

    private DevelopmentCard developmentCard;
    private ActionPlace actionPlace;
    private TowerFloor  towerFloor;

    @Before
    public void setUp(){

        developmentCard = new DevelopmentCard("Peppe",1,new LinkedList<Requirement>(),new LinkedList<Effect>(),"YELLOW",new HashMap<>());
        actionPlace = new ActionPlaceFixed(new LinkedList<Effect>(),2,1);

        towerFloor = new TowerFloor(actionPlace,developmentCard);
    }



    @Test(expected = IllegalStateException.class)
    public void putFamilyMemberTest(){

        FamilyMember familyMember = new FamilyMember("YELLOW","Peppe");
        towerFloor.addFamilyMember(familyMember);
        assertTrue(actionPlace.getMembers().size() == 1);

    }

    @Test
    public void putFamilyMemberTest2(){
        FamilyMember familyMember = new FamilyMember("YELLOW","Peppe");
        familyMember.setValue(10);
        towerFloor.addFamilyMember(familyMember);
        assertTrue(actionPlace.getMembers().size() == 1);
    }

    @Test(expected = IllegalStateException.class)
    public void denyAccessTest(){
        FamilyMember familyMember = new FamilyMember("YELLOW","Peppe");
        familyMember.setValue(10);
        FamilyMember familyMember1 = new FamilyMember("YELLOW","Peppe");
        familyMember1.setValue(10);
        towerFloor.addFamilyMember(familyMember1);
        towerFloor.addFamilyMember(familyMember);

        assertTrue(2 == actionPlace.getMembers().size() );
    }

    @Test
    public void removeFamilyMemberTest(){
        FamilyMember familyMember = new FamilyMember("YELLOW","Peppe");
        familyMember.setValue(10);
        towerFloor.addFamilyMember(familyMember);
        towerFloor.removeFamilyMembers();
        assertTrue(0 == actionPlace.getMembers().size() );
    }


}
