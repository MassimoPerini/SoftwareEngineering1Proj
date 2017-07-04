package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 03/07/2017.
 */
public class PlayerBoardTest {
    private PlayerBoard playerBoard;
    private Player player;
    private Game game;

    @Before
    public void setUp() throws IOException {
        game = new Game(0);
        game.addPlayer("gabri");
        player = game.getGameStatus().getPlayers().get("gabri");
        playerBoard = player.getPlayerBoard();
    }

    @Test
    public void functionalTest() throws IOException {
        int requiredValue2 = 2;
        int era2 = 1;
        String name2 = "devcards_f_en_c_2";
        List requirements2 = new ArrayList();
        List effects2 = new ArrayList();
        String idColour2 = "GREEN";
        List<Effect> immediateEffects2 = new ArrayList<>();
        List bonusEffects2 = new ArrayList();
        List malusEffects2 = new ArrayList();
        List prodHarvEffects2 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap2 = new HashMap<>();
        ResourceSet variation2 = new ResourceSet();
        variation2.variateResource(Resource.WOOD, 1);
        EffectOnResources effectOnResources2 = new EffectOnResources(variation2);
        immediateEffects2.add(effectOnResources2);
        EffectOnResources effect2 = new EffectOnResources( variation2);
        bonusEffects2.add(effect2);
        ProdHarvEffect prodHarvEffect2 = new ProdHarvEffect(malusEffects2, bonusEffects2);
        prodHarvEffects2.add(prodHarvEffect2);
        requestedMap2.put(requiredValue2, prodHarvEffects2);
        DevelopmentCard card2 = new DevelopmentCard(name2,era2, requirements2, immediateEffects2, idColour2, requestedMap2);

        int requiredValue3 = 3;
        int era3 = 1;
        String name3 = "devcards_f_en_c_3";
        List requirements3 = new ArrayList();
        List effects3 = new ArrayList();
        String idColour3 = "YELLOW";
        List<Effect> immediateEffects3 = new ArrayList<>();
        List bonusEffects3 = new ArrayList();
        List malusEffects3 = new ArrayList();
        List prodHarvEffects3 = new ArrayList();
        Map<Integer, List<ProdHarvEffect>> requestedMap3 = new HashMap<>();
        ResourceSet variation3 = new ResourceSet();
        variation3.variateResource(Resource.WOOD, 1);
        EffectOnResources effectOnResources3 = new EffectOnResources(variation3);
        immediateEffects3.add(effectOnResources3);
        EffectOnResources effect3 = new EffectOnResources( variation3);
        bonusEffects3.add(effect3);
        ProdHarvEffect prodHarvEffect3 = new ProdHarvEffect(malusEffects3, bonusEffects3);
        prodHarvEffects3.add(prodHarvEffect3);
        requestedMap3.put(requiredValue3, prodHarvEffects3);
        DevelopmentCard card3 = new DevelopmentCard(name3,era3, requirements3, immediateEffects3, idColour3, requestedMap3);

        playerBoard.addCard(card2, variation2);
        assertTrue(playerBoard.getDevelopmentCards("GREEN").size()==1);
        assertTrue(player.getPlayerBoard().getDevelopmentCards("GREEN").size()==1);

        playerBoard.addCard(card3, variation3);
        assertTrue(playerBoard.getDevelopmentCards("YELLOW").size()==1);
        assertTrue(playerBoard.getDevelopmentCards().size()==2);

    }
}
