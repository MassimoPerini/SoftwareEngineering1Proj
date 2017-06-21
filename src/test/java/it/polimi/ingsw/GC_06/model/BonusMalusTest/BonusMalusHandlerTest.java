package it.polimi.ingsw.GC_06.model.BonusMalusTest;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
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


    @Before

    public void setUp(){
        LinkedList<String> colours = new LinkedList<>();
        colours.add("WHITE");
        colours.add("ORANGE");
        colours.add("WHITE");
        bonusMalusOnAction = new BonusMalusOnAction("YELLOW",colours,ActionType.BOARD_ACTION_ON_TOWER,false,5);
        bonusMalusOnAction1 = new BonusMalusOnAction("YELLOW",colours,ActionType.BOARD_ACTION_ON_TOWER,true ,5);

        bonusMalusOnAction2 = new BonusMalusOnAction("BLUE",colours,ActionType.BOARD_ACTION_ON_TOWER,false,5);
        bonusMalusOnAction3 = new BonusMalusOnAction("YELLOW",colours,ActionType.GENERAL,false,-100);



        /** così abbiamo creato un player con il suo bonusMalusSet e il suo family member settato a 5*/
        familyMember.setValue(5);
        FamilyMember[] familyMembers = {familyMember};
        bonusMalusOnActions.add(bonusMalusOnAction);
        bonusMalusOnActions.add(bonusMalusOnAction1);
        bonusMalusOnActions.add(bonusMalusOnAction2);
        //bonusMalusOnActions.add(bonusMalusOnAction3);
        player = new Player("peppe", familyMembers);
        player.getBonusMalusSet().addActionBonusMalus(bonusMalusOnActions);

        /** adesso creiamo una azione */

    }


    @Test
    public void actionFilter(){

        /** esempio di azione su torre */
        BonusMalusHandler.filter(player,ActionType.BOARD_ACTION_ON_TOWER,"YELLOW",familyMember);
        assertTrue(15 == familyMember.getValue());
        assertTrue(player.getBonusMalusSet().getBonusMalusOnAction().get("BONUSMALUSONACTION").size() == 2);
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


        System.out.println(bonusMalusOnActions.size());

        BonusMalusHandler.filter(player,ActionType.MARKET_ACTION,familyMember);
        assertTrue(0 == familyMember.getValue());
        assertTrue(player.getBonusMalusSet().getBonusMalusOnAction().get("BONUSMALUSONACTION").size() == 3);

    }
}
