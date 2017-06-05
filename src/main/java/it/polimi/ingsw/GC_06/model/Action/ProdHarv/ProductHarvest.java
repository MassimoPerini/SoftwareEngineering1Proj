package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.ActionBoh;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnResourcesHarvProduction;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;



/**
 * Created by massimo on 30/05/17.
 */
public class ProductHarvest implements Action {

    private Player player;
    private int value;

    @Override
    public void execute() {

    }

    @Override
    public boolean isAllowed() {
        return false;
    }
/*
    public ProductHarvest(Player player, int value)
    {
        super();
        if (activateCards == null || player == null)
            throw new NullPointerException();

        this.activateCards = activateCards;
        this.player = player;
        this.automaticEffects = new LinkedList<>();
    }

    @Override
    public void execute() {

        List<ProdHarvEffect> ask = new LinkedList<>();

        for (DevelopmentCard developmentCard : activateCards)
        {
            List<ProdHarvEffect> prodHarvEffects = developmentCard.getProdHarvEffects();
            for (ProdHarvEffect prodHarvEffect : prodHarvEffects)
            {
                if (prodHarvEffects instanceof EffectOnResourcesHarvProduction)
                {
                    EffectOnResourcesHarvProduction defaultProdHarvEffect = (EffectOnResourcesHarvProduction) prodHarvEffect;
                    if (defaultProdHarvEffect.getVariateResource().isNegativeValuePresent())
                    {
                        //TODO aggiungere alla lista. Risposta???
                    //    ask.add()
                    }
                }
            }

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
        //    List<DefaultProdHarvEffect> prodHarvEffects = developmentCard.getProdHarvEffects();

        }

        return false;
    }
    */
}
