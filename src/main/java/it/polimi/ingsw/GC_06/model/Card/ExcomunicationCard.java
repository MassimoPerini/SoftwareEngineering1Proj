package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Effect.Effect;

import java.util.List;

/**
 * Created by gabri on 25/06/2017.
 */
public class ExcomunicationCard extends Card {
    private int era;
    private String name;
    private List<Effect> effects;

    public ExcomunicationCard(int era, String name, List<Effect> effects) {
        super(name);
        this.era = era;
        this.name = name;
        this.effects = effects;
    }


    public int getEra() {
        return era;
    }

    public String getName() {
        return name;
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
