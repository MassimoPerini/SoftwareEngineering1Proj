package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massimo on 30/05/17.
 */
public class ProdHarvEffect implements Effect {

    private List<Requirement> requirement;
    private List<Effect> effects;
    private int minPoints;

    public ProdHarvEffect(List<Requirement> requirement, List<Effect> effects, int minPoints)  {
        super();
        if (requirement==null || effects==null)
            throw new NullPointerException();

        this.requirement = requirement;
        this.effects = effects;
        this.minPoints = minPoints;
    }

    public void applyEffect(Player player, int playerMinPoints)
    {
    /*    if (!isAllowed(player, playerMinPoints))
            throw new IllegalStateException();
*/
        for (Effect effect:effects) {
            effect.execute(player);
        }
    }

    @Override
    public void execute(Player player) {

    }
}
