package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.BonusMalus.*;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHeroCard.BonusMalusType;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by giuseppe on 6/8/17.
 */

public class BonusMalusHandlerTest {


    private Player player;
    private FamilyMember familyMember = new FamilyMember("ORANGE","peppe");
    private BonusMalusOnAction bonusMalusOnAction;
    private List<BonusMalusOnAction> bonusMalusOnActions = new ArrayList<>();
    private BonusMalusOnAction bonusMalusOnAction1;
    private BonusMalusOnAction bonusMalusOnAction2;
    private BonusMalusOnAction bonusMalusOnAction3;
    private ResourceSet resourceSet = new ResourceSet();
    private BonusMalusOnAccess bonusMalusOnAccess;
    private BonusMalusOnAccess bonusMalusOnAccess1;

    private List<BonusMalusOnResources> bonusMalusOnResourcesList = new ArrayList<>();
    private BonusMalusOnResources bonusMalusOnResources;


    @Before
    public void setUp(){
        LinkedList<String> colours = new LinkedList<>();
        colours.add("WHITE");
        colours.add("ORANGE");
        colours.add("WHITE");
        bonusMalusOnAction = new BonusMalusOnAction("YELLOW",colours,ActionType.BOARD_ACTION_ON_TOWER,false,5);
        bonusMalusOnAction1 = new BonusMalusOnAction("YELLOW",colours,ActionType.BOARD_ACTION_ON_TOWER,true ,5);
        bonusMalusOnAccess = new BonusMalusOnAccess(ActionType.TOWER_ACTION,true,false);
        bonusMalusOnAccess1 = new BonusMalusOnAccess(ActionType.TOWER_ACTION,true,true);

        bonusMalusOnAction2 = new BonusMalusOnAction("BLUE",colours,ActionType.BOARD_ACTION_ON_TOWER,false,5);
        bonusMalusOnAction3 = new BonusMalusOnAction("YELLOW",colours,ActionType.GENERAL,false,-100);

        List<BonusMalusOnAccess> bonusMalusOnAccesses = new LinkedList<>();
        bonusMalusOnAccesses.add(bonusMalusOnAccess);
        bonusMalusOnAccesses.add(bonusMalusOnAccess1);
        resourceSet.variateResource(Resource.MONEY,10);
        resourceSet.variateResource(Resource.MILITARYPOINT,5);

        bonusMalusOnResources = new BonusMalusOnResources(Resource.MILITARYPOINT,-5,ActionType.RESOURCEACTION,false);
        bonusMalusOnResourcesList.add(bonusMalusOnResources);

        /** così abbiamo creato un player con il suo bonusMalusSet e il suo family member settato a 5*/
        familyMember.setValue(5);
        FamilyMember[] familyMembers = {familyMember};
        bonusMalusOnActions.add(bonusMalusOnAction);
        bonusMalusOnActions.add(bonusMalusOnAction1);
        bonusMalusOnActions.add(bonusMalusOnAction2);
        //bonusMalusOnActions.add(bonusMalusOnAction3);
        player = new Player("peppe", familyMembers);
        player.getBonusMalusSet().addActionBonusMalus(bonusMalusOnActions);
        player.getBonusMalusSet().addResourceBonusMalus(bonusMalusOnResourcesList);
        player.getBonusMalusSet().addAccessBonusMalus(bonusMalusOnAccesses);

        ResourceSet malusSet = new ResourceSet();
         malusSet.variateResource(Resource.MONEY,-4);
         malusSet.variateResource(Resource.MILITARYPOINT,-4);

        //bonusMalusOnResources = new BonusMalusOnResources()

    }


    @Test
    public void actionFilter(){

        /** esempio di azione su torre */
        BonusMalusHandler.filter(player,ActionType.BOARD_ACTION_ON_TOWER,"YELLOW",familyMember);
        assertTrue(15 == familyMember.getValue());
       // assertTrue(player.getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size() == 2);
    }

    @Test
    public void secondActionFilter(){
        LinkedList<String> colours = new LinkedList<>();
        colours.add("WHITE");
        colours.add("ORANGE");
        colours.add("WHITE");
        bonusMalusOnAction3 = new BonusMalusOnAction("YELLOW",colours,ActionType.GENERAL,false,-100);

        List<BonusMalusOnAction> bonusMalusOnActionList = new ArrayList<>();
        bonusMalusOnActionList.add(bonusMalusOnAction3);
        player.getBonusMalusSet().addActionBonusMalus(bonusMalusOnActionList);



        BonusMalusHandler.filter(player,ActionType.BOARD_ACTION_ON_MARKET,null,familyMember);
//        assertTrue(0 == familyMember.getValue());
//        assertTrue(player.getBonusMalusSet().getBonusMalusOnAction().get(BonusMalusType.BONUSMALUSONACTION).size() == 3);

    }

    @Test
    public void thirdActionFilter(){



        BonusMalusHandler.filter(player,ActionType.RESOURCEACTION,resourceSet);

        assertTrue(0 == player.getBonusMalusSet().getBonusMalusOnResources().get(BonusMalusType.BONUSMALUSONRESOURCE).size());
        assertTrue(0 == resourceSet.getResourceAmount(Resource.MILITARYPOINT));
        assertTrue(10 == resourceSet.getResourceAmount(Resource.MONEY));

    }

    @Test
    public void accessFilter(){

        boolean result = false;
        result = BonusMalusHandler.filter(player,ActionType.TOWER_ACTION,result);

        assertTrue(result);

    }
}
