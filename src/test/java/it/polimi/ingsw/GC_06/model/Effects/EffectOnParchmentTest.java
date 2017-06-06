package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Effect.EffectOnParchment;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnParchmentTest {
    private EffectOnParchment effectOnParchment;
    private ResourceSet parchment1,parchment2,parchment3,parchment4,parchment5;
    private Player player;

    @Before
    public void setUp() {
        Game.clearForTesting();
        Game.getInstance().addPlayer("gabriele");
        player = Game.getInstance().getGameStatus().getCurrentPlayer();
        parchment1 = new ResourceSet();
        parchment1.variateResource(Resource.WOOD,1);
        parchment1.variateResource(Resource.STONE,1);
        parchment1.variateResource(Resource.MONEY,1);
        parchment2.variateResource(Resource.SERVANT,2);
        parchment2.variateResource(Resource.MONEY,1);
        parchment3.variateResource(Resource.MONEY,3);
        parchment4.variateResource(Resource.MILITARYPOINT,2);
        parchment4.variateResource(Resource.MONEY,1);
        parchment5.variateResource(Resource.FAITHPOINT,1);
        parchment5.variateResource(Resource.MONEY,1);
        effectOnParchment = new EffectOnParchment(parchment1,parchment2,parchment3,parchment4,parchment5);

    }

    @Test
    public void correctChoicePositive() {
        //qui si sceglie il parchment 1
        effectOnParchment.execute(player);
        ResourceSet r = player.getResourceSet();
        assertTrue(r.getResourceAmount(Resource.WOOD)==1);
        assertTrue(r.getResourceAmount(Resource.STONE)==1);
        assertTrue(r.getResourceAmount(Resource.MONEY)==1);
    }

    @Test
    public void incorrectChoice() {
        // TODO è da scrivere ol tanto da CLI non si può fare una scelta non consentita?
    }


}
