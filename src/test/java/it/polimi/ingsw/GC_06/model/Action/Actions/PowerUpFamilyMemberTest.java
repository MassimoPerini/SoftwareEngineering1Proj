package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Action.PickCard.DefaulEventManagerFake;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusOnSettings;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by giuseppe on 7/1/17.
 */
public class PowerUpFamilyMemberTest {

    private Player player;
    private Game game;
    private PowerUpFamilyMember powerUpFamilyMember;
    private FamilyMember familyMember;
    @Before
    public void setUp() throws IOException {

        game = new Game(0);
        game.addPlayer("peppe");
        game.start(new DefaulEventManagerFake());
        player = game.getGameStatus().getPlayers().get("peppe");
        familyMember = player.getFamilyMembers()[0];
        powerUpFamilyMember = new PowerUpFamilyMember(player,familyMember,5);
    }

    @Test
    public void testWithoutBonus(){

        int oldQuantity = player.getResourceSet().getResourceAmount(Resource.SERVANT);

        powerUpFamilyMember.execute();

        assertTrue(player.getResourceSet().getResourceAmount(Resource.SERVANT) == oldQuantity - 5);

    }

    @Test

    public void testWithMalus(){

        BonusMalusOnSettings bonusMalusOnSettings = new BonusMalusOnSettings(2,null, ActionType.POWER_UP_FAMILY_MEMBER);

        List<BonusMalusOnSettings> bonusMalusOnSettingsList = new LinkedList<>();
        bonusMalusOnSettingsList.add(bonusMalusOnSettings);

        player.getBonusMalusSet().addBonusMalusOnSettings(bonusMalusOnSettingsList);

        int oldQuantity = player.getResourceSet().getResourceAmount(Resource.SERVANT);

        powerUpFamilyMember.execute();

        assertTrue(player.getResourceSet().getResourceAmount(Resource.SERVANT) == oldQuantity - 10);

    }


}