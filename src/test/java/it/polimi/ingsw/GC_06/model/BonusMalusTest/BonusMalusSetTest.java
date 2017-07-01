package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.BonusMalus.*;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by giuseppe on 6/17/17.
 */
public class BonusMalusSetTest {

    private BonusMalusOnCost bonusMalusOnCost;
    private ArrayList<BonusMalusOnAction> listBonusMalusOnAction;
    private BonusMalusOnAction bonusMalusOnAction;
    private BonusMalusOnAction bonusMalusOnAction1;
    private BonusMalusOnEnd bonusMalusOnEnd;
    private BonusMalusOnResources bonusMalusOnResources;
    private BonusMalusOnSettings bonusMalusOnSettings;

    private List<BonusMalusOnResources> bonusMalusOnResourcesList = new LinkedList<>();
    private  List<BonusMalusOnSettings> bonusMalusOnSettingsList = new LinkedList<>();
    private List<BonusMalusOnCost> listBonusMalusOnCost;
    private List<BonusMalusOnEnd> listBonusMalusOnEnd = new LinkedList<>();

    private BonusMalusSet bonusMalusSet;
    private BonusMalusSet bonusMalusSet1;





    @Before
    public void setUp() {


        bonusMalusOnAction = new BonusMalusOnAction("yellow", new LinkedList<String>(), ActionType.PAYCARDACTION, false , 5);
        bonusMalusOnAction1 = new BonusMalusOnAction("green", new LinkedList<String>(), ActionType.ENDACTION, false, 5);
        ResourceSet resourceSet = new ResourceSet();
        resourceSet.variateResource(Resource.MONEY,10);
        bonusMalusOnCost = new BonusMalusOnCost(resourceSet,new LinkedList<>(),ActionType.PAYCARDACTION,false);
        bonusMalusOnEnd = new BonusMalusOnEnd(Resource.FAITHPOINT, new LinkedList<Resource>(),ActionType.END_GAME,1,false);
        bonusMalusOnResources = new BonusMalusOnResources(Resource.MILITARYPOINT,5,ActionType.PRODUCTION_ACTION,false);
       // bonusMalusOnSettings = new BonusMalusOnSettings(5,3,new LinkedList<String>(),ActionType.ENDACTION);

        listBonusMalusOnCost = new ArrayList<>();
        listBonusMalusOnAction = new ArrayList<>();


        bonusMalusOnResourcesList.add(bonusMalusOnResources);
        listBonusMalusOnEnd.add(bonusMalusOnEnd);
        listBonusMalusOnAction.add(bonusMalusOnAction);
        listBonusMalusOnAction.add(bonusMalusOnAction1);
        listBonusMalusOnCost.add(bonusMalusOnCost);
        bonusMalusOnSettingsList.add(bonusMalusOnSettings);



        bonusMalusSet = new BonusMalusSet();
        bonusMalusSet1 = new BonusMalusSet();

    }


    @Test

    public void additionTest() {

        bonusMalusSet.addActionBonusMalus(listBonusMalusOnAction);
        bonusMalusSet.addCostBonusMalus(listBonusMalusOnCost);
        bonusMalusSet.addEndBonusMalus(listBonusMalusOnEnd);
        bonusMalusSet.addResourceBonusMalus(bonusMalusOnResourcesList);
        bonusMalusSet.addBonusMalusOnSettings(bonusMalusOnSettingsList);

        assertTrue(2 == bonusMalusSet.getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size());
        assertTrue(1==  bonusMalusSet.getBonusMalusOnCost().get(BonusMalusType.BONUSMALUSONCOST).size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnEnd().get(BonusMalusType.BONUSMALUSONEND).size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnResources().get(BonusMalusType.BONUSMALUSONRESOURCE).size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnResources().get(BonusMalusType.BONUSMALUSONRESOURCE).size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnSetting().get(BonusMalusType.BONUSMALUSONSETTING).size());

        bonusMalusSet1.addActionBonusMalus(listBonusMalusOnAction);
        bonusMalusSet1.addCostBonusMalus(listBonusMalusOnCost);
        bonusMalusSet1.addEndBonusMalus(listBonusMalusOnEnd);
        bonusMalusSet1.addResourceBonusMalus(bonusMalusOnResourcesList);
        bonusMalusSet1.addBonusMalusOnSettings(bonusMalusOnSettingsList);




        assertTrue(2 == bonusMalusSet1.getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size());
        assertTrue(1==  bonusMalusSet1.getBonusMalusOnCost().get(BonusMalusType.BONUSMALUSONCOST).size());
        assertTrue(1 == bonusMalusSet1.getBonusMalusOnEnd().get(BonusMalusType.BONUSMALUSONEND).size());
        assertTrue(1 == bonusMalusSet1.getBonusMalusOnSetting().get(BonusMalusType.BONUSMALUSONSETTING).size());

        bonusMalusSet.joinSet(bonusMalusSet1);

        assertTrue(4 == bonusMalusSet.getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size());
        assertTrue(2 == bonusMalusSet.getBonusMalusOnCost().get(BonusMalusType.BONUSMALUSONCOST).size());
        assertTrue(2 == bonusMalusSet.getBonusMalusOnEnd().get(BonusMalusType.BONUSMALUSONEND).size());
        assertTrue(2 == bonusMalusSet.getBonusMalusOnEnd().get(BonusMalusType.BONUSMALUSONEND).size());
        assertTrue(2 == bonusMalusSet.getBonusMalusOnSetting().get(BonusMalusType.BONUSMALUSONSETTING).size());

        /**int i =  bonusMalusSet.removeBonusMalusCost(bonusMalusSet.getBonusMalusOnCost().get(BonusMalusType.BONUSMALUSONCOST),1);
        bonusMalusSet.removeBonusMalusAction(bonusMalusSet.getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION),1);
        bonusMalusSet.removeBonusMalusEnd(bonusMalusSet.getBonusMalusOnEnd().get(BonusMalusType.BONUSMALUSONEND),1);
        bonusMalusSet.removeBonusMalusSetting(bonusMalusSet.getBonusMalusOnSetting().get(BonusMalusType.BONUSMALUSONSETTING),1);
        bonusMalusSet.removeBonusMalusResources(bonusMalusSet.getBonusMalusOnResources().get(BonusMalusType.BONUSMALUSONRESOURCE),1);

        assertTrue(i == 0);
        assertTrue(1
                == bonusMalusSet.getBonusMalusOnCost().get(BonusMalusType.BONUSMALUSONCOST).size());
        assertTrue(3 == bonusMalusSet.getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnEnd().get(BonusMalusType.BONUSMALUSONEND).size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnResources().get(BonusMalusType.BONUSMALUSONRESOURCE).size());
        assertTrue(1 == bonusMalusSet.getBonusMalusOnSetting().get(BonusMalusType.BONUSMALUSONSETTING).size());
 */




    }





}
