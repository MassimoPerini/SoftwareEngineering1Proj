package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Effect.Effect;

import java.util.List;

/**
 * Created by gabri on 25/06/2017.
 * rappresenta una scomunica nella fase di rapporto al vaticano
 */
public class ExcomunicationCard extends Card {
    private int era;
    private List<Effect> effects;

    /**
     *
     * @param era era della scomunica
     * @param name nome della scomunica
     * @param effects effetti della scomunica
     */
    public ExcomunicationCard(int era, String name, List<Effect> effects) {
        super(name);
        this.era = era;
        this.effects = effects;
    }


    public int getEra() {
        return era;
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
