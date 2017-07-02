package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 02/07/2017.
 */
public class DevelopmentCardTest {
    private DevelopmentCard card;
    private Game game;

    @Before
    public void SetUp() throws IOException {
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
        card = new DevelopmentCard(name2,era2, requirements2, immediateEffects2, idColour2, requestedMap2);
        game = new Game(0);
        game.addPlayer("gabri");

    }

    @Test
    public void getImmediateEffects() throws IOException {
        List<Effect> effects = card.getImmediateEffects();
        assertTrue(effects.size()==1);
    }

    @Test
    public void getProdHarvEffects() throws IOException {
        assertTrue(card.getProdHarvEffects().size()==1);
    }

    @Test
    public void effectsExecution() throws IOException {
        Player player = game.getGameStatus().getPlayers().get("gabri");
        int preQuantity = player.getResourceSet().getResourceAmount(Resource.WOOD);
        try {
            card.getImmediateEffects().get(0).execute(player, game);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(player.getResourceSet().getResourceAmount(Resource.WOOD)==preQuantity+1);
    }
}
