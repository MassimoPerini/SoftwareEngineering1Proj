package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

/**
 * Created by giuseppe on 6/8/17.
 */
public class BonusMalusOnActionTest {

    private FamilyMember familyMember;
    private LinkedList<String> familyMemberColour = new LinkedList<>();
    private BonusMalusOnAction bonusMalusOnAction;


    @Before
    public void setUp(){
        familyMemberColour.add("White");
        familyMemberColour.add("Neuter");
        familyMemberColour.add("Orange");
        familyMemberColour.add("Black");

        familyMember = new FamilyMember("Orange","peppe");
        familyMember.setValue(5);
        bonusMalusOnAction = new BonusMalusOnAction("Neuter",-4, ActionType.TOWERACTION,familyMemberColour);

    }

    @Test
    public void secondTest(){

        bonusMalusOnAction.modify(familyMember);
        assertTrue(familyMember.getValue()==1);
       // assertTrue(bonusMalusOnAction.checkFamilyMember(familyMember));
    }



}
