package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnConditions implements Effect{

    private Resource modifiedResource;
    private int multiplier;
    private String colour;

    public EffectOnConditions(Resource modifiedResource, int multiplier, String colour) {
        this.modifiedResource = modifiedResource;
        this.multiplier = multiplier;
        this.colour = colour;

    }

    @Override
    public void execute(Player player,Game game) {
        int size = (player.getPlayerBoard().getDevelopmentCards(colour)).size();
        int variation = multiplier * size;
        player.getResourceSet().variateResource(modifiedResource,variation);

    }

}
