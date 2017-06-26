package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusSet;
import it.polimi.ingsw.GC_06.model.Effect.DonateBonusMalusEffect;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by giuseppe on 6/24/17.
 */
public class DonateBonusMalusTest {

    private Player player;
    private Effect donateBonusMalus;
    private Game game;
    private BonusMalusOnAction bonusMalusOnAction;
    private ArrayList<BonusMalusOnAction> listBonusMalusOnAction = new ArrayList<>();
    private BonusMalusSet bonusMalusSet = new BonusMalusSet();



    @Before
    public void setUp() throws IOException {

        bonusMalusOnAction = new BonusMalusOnAction("yellow", new LinkedList<String>(), ActionType.PAYCARDACTION, false , 5);

        listBonusMalusOnAction.add(bonusMalusOnAction);
        bonusMalusSet.addActionBonusMalus(listBonusMalusOnAction);

        FamilyMember[] familyMembers = {};
        player = new Player("Peppe",familyMembers);
        game = new Game(0);
        donateBonusMalus = new DonateBonusMalusEffect(bonusMalusSet);
    }

    @Test
    public void firstTest(){
        try {
            donateBonusMalus.execute(player,game);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
