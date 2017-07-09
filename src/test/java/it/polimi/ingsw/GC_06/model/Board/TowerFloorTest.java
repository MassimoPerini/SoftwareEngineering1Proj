package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by giuseppe on 6/24/17.
 */
public class TowerFloorTest {

    private ActionPlace actionPlace;
    private ActionPlace actionPlaceFixed;
    private DevelopmentCard developmentCard;
    private TowerFloor towerFloor;
    private FamilyMember familyMember;
    private TowerFloor fixedTowerFloor;

    @Before
    public void setUp(){
        Setting.getInstance().addPath("settings/bundle");
        actionPlace = new ActionPlace(new LinkedList<Effect>(),4);
        developmentCard = new DevelopmentCard("peppe",1,new LinkedList<Requirement>(), new LinkedList<Effect>(), "yellow",new HashMap<Integer,List<ProdHarvEffect>>());
        towerFloor = new TowerFloor(actionPlace,developmentCard);
        familyMember = new FamilyMember("green","peppe");
        actionPlaceFixed = new ActionPlaceFixed(new LinkedList<Effect>(),2,1);
        fixedTowerFloor = new TowerFloor(actionPlaceFixed,developmentCard);
    }

    @Test
    public void addFamilyMemberTest(){
        towerFloor.addFamilyMember(familyMember);
        assertTrue(towerFloor.getActionPlace().getMembers().size() == 1);
        fixedTowerFloor.addFamilyMember(familyMember);
        assertTrue(towerFloor.getActionPlace().getMembers().size() == 1);


    }

    @Test
    public void notAddFamilyMember(){
        fixedTowerFloor.addFamilyMember(familyMember);
        fixedTowerFloor.addFamilyMember(familyMember);
        assertTrue(towerFloor.getActionPlace().getMembers().size() == 0);
    }

    @Test

    public void removeFamilyMemebr(){

        towerFloor.addFamilyMember(familyMember);
        towerFloor.removeFamilyMembers();

        assertTrue(0 == actionPlace.getMembers().size());
    }

    @Test

    public void pickCard(){

        DevelopmentCard developmentCard = towerFloor.pickCard();
        assertTrue(developmentCard.getIdColour().equals("yellow"));
    }
}
