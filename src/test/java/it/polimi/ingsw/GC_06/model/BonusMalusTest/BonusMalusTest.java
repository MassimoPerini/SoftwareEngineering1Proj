package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;



/**
 * Created by giuseppe on 6/21/17.
 */
public class BonusMalusTest {

    private LinkedList<String> colours = new LinkedList<>();
    private BonusMalusOnAction bonusMalusOnAction;
    private FamilyMember familyMember;


    @Before
    public void setUp(){
        colours.add("WHITE");
        colours.add("ORANGE");
        familyMember = new FamilyMember("WHITE","peppe");
        familyMember.imposta(5);
        bonusMalusOnAction = new BonusMalusOnAction("",colours, ActionType.PAYCARDACTION,false,5);

    }

    @Test

    public void firstTest(){
        bonusMalusOnAction.modify(familyMember);
        assertTrue(5 == familyMember.getValue());
    }
}
