package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 30/05/17.
 */
public class ProductHarvest implements Action {

    private List<DevelopmentCard> activateCards;
    private Player player;

    public ProductHarvest(List<DevelopmentCard> activateCards, Player player)
    {
        super();
        if (activateCards == null || player == null)
            throw new NullPointerException();

        this.activateCards = activateCards;
        this.player = player;
    }

    @Override
    public void execute() {

        for (DevelopmentCard developmentCard : activateCards)
        {
            List<Effect> prodHarvEffects = developmentCard.getProdHarvEffects();
            if (prodHarvEffects.size() > 1)
            {
                //TODO chiedere che effetto attivare
            }
            else if (prodHarvEffects.size() == 1)
            {
                //check valid effects
            }
        }

    }

    @Override
    public boolean isAllowed() {

        //cheeck in che modo eseguire + effetti, attivazione contemporanea

        for (DevelopmentCard developmentCard : activateCards)
        {
        //    List<ProdHarvEffect> prodHarvEffects = developmentCard.getProdHarvEffects();

        }

        return false;
    }
}
