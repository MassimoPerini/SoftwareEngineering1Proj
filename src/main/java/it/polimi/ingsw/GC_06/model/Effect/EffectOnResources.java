package it.polimi.ingsw.GC_06.model.Effect;


import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/20/17.
 * la classse è un effetto che permette di modificare le risorse di un giocatore
 */
public class EffectOnResources implements ProdHarvMalusEffect {

    private ResourceSet resourceSet;
    //private final ActionType ACTION_TYPE = ActionType.RESOURCEACTION;
    
    public EffectOnResources(ResourceSet resourceSet) {
    	super();
        this.resourceSet = resourceSet;

    }

    /**
     * modifica il resourceSet del giocatore a cui si applica l'effetto
     * @param player
     * @param game
     */
    public void execute(Player player,Game game){
        BonusMalusHandler.filter(player,ActionType.RESOURCEACTION,this.resourceSet);
        player.variateResource(this.resourceSet);
    }


    public ResourceSet getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(ResourceSet resourceSet) {
        this.resourceSet = resourceSet;
    }

    @Override
    public boolean isAllowed(Player player) {
        return player.getResourceSet().isIncluded(resourceSet);
    }
}
