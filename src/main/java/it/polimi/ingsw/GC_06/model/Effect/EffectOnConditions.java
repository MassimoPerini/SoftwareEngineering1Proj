package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.PlayType;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnConditions implements Effect{

    private PlayType playType;
    private Resource modifiedResource;
    private int multiplier;
    private String colour;

    public EffectOnConditions(Resource modifiedResource, int multiplier, String colour, PlayType playType) {
        this.modifiedResource = modifiedResource;
        this.multiplier = multiplier;
        this.colour = colour;
        this.playType = playType;
    }

    @Override
    public void execute(Player player) {
        int size = (player.getPlayerBoard().getColouredCards(colour)).size();
        int variation = multiplier * size;
        player.getResourceSet().variateResource(modifiedResource,variation);

    }

}
