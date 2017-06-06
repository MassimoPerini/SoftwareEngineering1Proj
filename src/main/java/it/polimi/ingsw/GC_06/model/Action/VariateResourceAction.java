package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 06/06/17.
 */
public class VariateResourceAction implements Action {

    private EffectOnResources effectOnResources;
    private Player player;

    public VariateResourceAction(ResourceSet resourceSet, Player player)
    {
        effectOnResources = new EffectOnResources(resourceSet);
        this.player = player;
    }

    @Override
    public void execute() {
        effectOnResources.execute(player);

    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
