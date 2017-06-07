package it.polimi.ingsw.GC_06.model.Effects;

import it.polimi.ingsw.GC_06.model.Effect.EffectOnParchment;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.StateName;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnParchmentTest {
    private EffectOnParchment effectOnParchment;
    private ArrayList<ResourceSet> parchments;
    private Player player;

    @Before
    public void setUp() {
        Game.clearForTesting();
        Game.getInstance().addPlayer("gabriele");
        player = Game.getInstance().getGameStatus().getCurrentPlayer();
        parchments = new ArrayList<>();
        ResourceSet parchment1 = new ResourceSet();
        parchment1.variateResource(Resource.WOOD,1);
        parchment1.variateResource(Resource.STONE,1);
        parchment1.variateResource(Resource.MONEY,1);
        parchments.add(parchment1);
        ResourceSet parchment2 = new ResourceSet();
        parchment2.variateResource(Resource.SERVANT,2);
        parchment2.variateResource(Resource.MONEY,1);
        parchments.add(parchment2);
        ResourceSet parchment3 = new ResourceSet();
        parchment3.variateResource(Resource.MONEY,3);
        parchments.add(parchment3);
        ResourceSet parchment4 = new ResourceSet();
        parchment4.variateResource(Resource.MILITARYPOINT,2);
        parchment4.variateResource(Resource.MONEY,1);
        ResourceSet parchment5 = new ResourceSet();
        parchment5.variateResource(Resource.FAITHPOINT,1);
        parchment5.variateResource(Resource.MONEY,1);
        effectOnParchment = new EffectOnParchment(parchments);

    }

    @Test
    public void correctTransition() {
        //effectOnParchment.execute(player);
        //assertTrue(Game.getInstance().getGameStatus().getCurrentStatus()== StateName.IDLE);
        //TODO capire come aggiungere le transizioni necessarie alla tabella
    }

}
