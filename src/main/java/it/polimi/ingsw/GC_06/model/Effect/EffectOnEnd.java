package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 6/16/17.
 * effetto da applicare durante l'endGame
 */
public class EffectOnEnd implements Effect{

    private ResourceSet endResourceAddiction;

    public EffectOnEnd(ResourceSet endResourceAddiction) {
        this.endResourceAddiction = endResourceAddiction;
    }

    /**
     * aggiunge le risorse al giocatore ma solo durante la fase di EndGame
     * @param player
     * @param game
     */
    @Override
    public void execute(Player player, Game game) {
        player.variateAddAtTheEnd(endResourceAddiction);
    }
}
