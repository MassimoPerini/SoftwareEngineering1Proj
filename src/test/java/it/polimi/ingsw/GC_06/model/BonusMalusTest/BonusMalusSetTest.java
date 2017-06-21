package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.*;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by giuseppe on 6/17/17.
 */
public class BonusMalusSetTest {

    private BonusMalusOnCost bonusMalusOnCost;
    private BonusMalusOnEnd bonusMalusOnEnd;
    private BonusMalusOnResources bonusMalusOnResource;
    private ArrayList<BonusMalusOnAction> listBonusMalusOnAction;
    private BonusMalusOnAction bonusMalusOnAction;
    private BonusMalusOnAction bonusMalusOnAction1;

    private BonusMalusSet bonusMalusSet;
    private BonusMalusSet bonusMalusSet1;
    private List<BonusMalusOnCost> listBonusMalusOnCost;
    private List<BonusMalusOnEnd> bonusMalusOnEnds;
    private List<BonusMalusOnResources> bonusMalusOnResources;


    @Before
    public void setUp() {


        bonusMalusOnAction = new BonusMalusOnAction("yellow", new LinkedList<String>(), ActionType.PAYCARDACTION, false , 5);
        bonusMalusOnAction1 = new BonusMalusOnAction("green", new LinkedList<String>(), ActionType.ENDACTION, false, 5);
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY,10);
        bonusMalusOnCost = new BonusMalusOnCost(resourceSet,new LinkedList<>(),ActionType.PAYCARDACTION,false);
        bonusMalusOnEnd = new BonusMalusOnEnd(1,1,new LinkedList<>(),ActionType.PAYCARDACTION);
        bonusMalusOnResource = new BonusMalusOnResources(resourceSet,2,ActionType.PAYCARDACTION,Resource.END_POINTS);

        bonusMalusOnEnds = new ArrayList<>();
        listBonusMalusOnCost = new ArrayList<>();
        listBonusMalusOnAction = new ArrayList<>();
        bonusMalusOnResources = new ArrayList<>();

        listBonusMalusOnAction.add(bonusMalusOnAction);
        listBonusMalusOnAction.add(bonusMalusOnAction1);
        listBonusMalusOnCost.add(bonusMalusOnCost);
        bonusMalusOnEnds.add(bonusMalusOnEnd);
        bonusMalusOnResources.add(bonusMalusOnResource);



        bonusMalusSet = new BonusMalusSet();
        bonusMalusSet1 = new BonusMalusSet();

    }


    @Test

    public void additionTest() {

        bonusMalusSet.addActionBonusMalus(listBonusMalusOnAction);
        bonusMalusSet.addCostBonusMalus(listBonusMalusOnCost);
        bonusMalusSet.addResourceBonusMalus(bonusMalusOnResources);
        bonusMalusSet.addEndBonusMalus(bonusMalusOnEnds);

        assertTrue(2 == bonusMalusSet.getBonusMalusOnAction().get("BONUSMALUSONACTION").size());
        assertTrue(1==  bonusMalusSet.getBonusMalusOnCost().get("BONUSMALUSONCOST").size());
        assertTrue(1==  bonusMalusSet.getBonusMalusOnResources().get("BONUSMALUSONRESOURCE").size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnEnd().get("BONUSMALUSONEND").size());


        bonusMalusSet1.addActionBonusMalus(listBonusMalusOnAction);
        bonusMalusSet1.addCostBonusMalus(listBonusMalusOnCost);
        bonusMalusSet1.addResourceBonusMalus(bonusMalusOnResources);
        bonusMalusSet1.addEndBonusMalus(bonusMalusOnEnds);

        assertTrue(2 == bonusMalusSet1.getBonusMalusOnAction().get("BONUSMALUSONACTION").size());
        assertTrue(1==  bonusMalusSet1.getBonusMalusOnCost().get("BONUSMALUSONCOST").size());
        assertTrue(1==  bonusMalusSet1.getBonusMalusOnResources().get("BONUSMALUSONRESOURCE").size());
        assertTrue(1 == bonusMalusSet1.getBonusMalusOnEnd().get("BONUSMALUSONEND").size());


        bonusMalusSet.joinSet(bonusMalusSet1);

        assertTrue(4 == bonusMalusSet.getBonusMalusOnAction().get("BONUSMALUSONACTION").size());
        assertTrue(2 == bonusMalusSet.getBonusMalusOnCost().get("BONUSMALUSONCOST").size());
        assertTrue(2 == bonusMalusSet.getBonusMalusOnResources().get("BONUSMALUSONRESOURCE").size());
        assertTrue(2 == bonusMalusSet.getBonusMalusOnEnd().get("BONUSMALUSONEND").size());


        int i =  bonusMalusSet.removeBonusMalusCost(bonusMalusSet.getBonusMalusOnCost().get("BONUSMALUSONCOST"),1);
        bonusMalusSet.removeBonusMalusEnd(bonusMalusSet.getBonusMalusOnEnd().get("BONUSMALUSONEND"),1);
        bonusMalusSet.removeBonusMalusAction(bonusMalusSet.getBonusMalusOnAction().get("BONUSMALUSONACTION"),1);
        bonusMalusSet.removeBonusMalusResources(bonusMalusSet.getBonusMalusOnResources().get("BONUSMALUSONRESOURCE"),1);



        assertTrue(i == 0);
        assertTrue(1 == bonusMalusSet.getBonusMalusOnCost().get("BONUSMALUSONCOST").size());
        assertTrue(3 == bonusMalusSet.getBonusMalusOnAction().get("BONUSMALUSONACTION").size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnResources().get("BONUSMALUSONRESOURCE").size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnEnd().get("BONUSMALUSONEND").size());






    }





}
