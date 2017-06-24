package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/28/17.
 */
public class EffectOnConditions implements Effect{

    private Resource modifiedResource;
    private double multiplier;
    private String conditioningObject;

    public EffectOnConditions(Resource modifiedResource, double multiplier, String conditioningObject) {
        this.modifiedResource = modifiedResource;
        this.multiplier = multiplier;
        this.conditioningObject = conditioningObject;

    }

    @Override
    public void execute(Player player,Game game) {
        if (conditioningObject!="MILITARYPOINTS" && conditioningObject!="FAITHPOINTS") {
            int size = (player.getPlayerBoard().getDevelopmentCards(conditioningObject)).size();
            int variation = (int)(multiplier * size);
            player.getResourceSet().variateResource(modifiedResource,variation);
        }
        else if (conditioningObject=="MILITARYPOINTS") {
            int size = (player.getResourceSet().getResourceAmount(Resource.MILITARYPOINT));
            int variation = (int)(multiplier * size);
            player.getResourceSet().variateResource(modifiedResource,variation);
        }
        else {
            int size = (player.getResourceSet().getResourceAmount(Resource.FAITHPOINT));
            int variation = (int)(multiplier * size);
            player.getResourceSet().variateResource(modifiedResource,variation);
        }

    }

}
