package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;



/**
 * Created by giuseppe on 6/21/17.
 */
public class BonusMalusOnActionTest {

    private LinkedList<String> colours = new LinkedList<>();
    private BonusMalusOnAction bonusMalusOnAction;
    private FamilyMember familyMember;


    @Before
    public void setUp(){
        Setting.getInstance().addPath("settings/bundle");
        colours.add("WHITE");

        familyMember = new FamilyMember("WHITE","peppe");
        familyMember.setValue(5);
        bonusMalusOnAction = new BonusMalusOnAction("",colours, ActionType.PAYCARDACTION,false,5);

    }

    @Test
    public void firstTest(){
        bonusMalusOnAction.modify(familyMember);
        assertTrue(10 == familyMember.getValue());
    }
}
