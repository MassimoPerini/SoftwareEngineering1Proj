package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnResources;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by giuseppe on 6/17/17.
 */
public class BonusMalusSetTest {

    private HashMap<String,ArrayList<BonusMalusOnResources>> bonusMalusOnResources;
    private HashMap<String,ArrayList<BonusMalusOnResources>> bonusMalusOnResources1;
    private HashMap<String,ArrayList<BonusMalusOnAction>> bonusMalusOnActionMap;
    private ArrayList<BonusMalusOnAction> listBonusMalusOnAction;
    private BonusMalusOnAction bonusMalusOnAction;
    private BonusMalusOnAction bonusMalusOnAction1;

    private BonusMalusSet bonusMalusSet;
    private BonusMalusSet bonusMalusSet1;

    @Before
    public void setUp(){


        bonusMalusOnAction = new BonusMalusOnAction("yellow",5,ActionType.PAYCARDACTION, new LinkedList<String>());
        bonusMalusOnAction1 = new BonusMalusOnAction("green",5,ActionType.ENDACTION,new LinkedList<String>());
        listBonusMalusOnAction = new ArrayList<>();
        listBonusMalusOnAction.add(bonusMalusOnAction);
        listBonusMalusOnAction.add(bonusMalusOnAction1);


        /**creo il mio prima malus set */

        bonusMalusSet = new BonusMalusSet();
        bonusMalusSet1 = new BonusMalusSet();

    }


    @Test

    public void additionTest(){
        /** questo mi verifica le aggiunzione al set */
        bonusMalusSet.addActionBonusMalus(listBonusMalusOnAction);
        assertTrue(2==bonusMalusSet.getBonusMalusOnAction().get("BONUSMALUSONACTION").size());
        bonusMalusSet1.addActionBonusMalus(listBonusMalusOnAction);
        bonusMalusSet1.addActionBonusMalus(listBonusMalusOnAction);
        assertTrue(4 == bonusMalusSet1.getBonusMalusOnAction().get("BONUSMALUSONACTION").size());

    }

    public void joinTest(){

        bonusMalusSet.joinSet(bonusMalusSet1);

        assertTrue(6 == bonusMalusSet.getBonusMalusOnAction().get("BONUSMALUSONACTION").size());
    }
}
