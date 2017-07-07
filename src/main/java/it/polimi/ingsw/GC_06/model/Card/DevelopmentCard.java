package it.polimi.ingsw.GC_06.model.Card;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 12/05/17.
 */

/**
 * @author massimo
 * This class is a DevelopmentCard
 */
public class
DevelopmentCard extends Card
{
    private int era;
    private List<Requirement> requirements;
    private final List<Effect> immediateEffects;
    private final String idColour;
    // l'intero rappresenta il required value per l'azione di produzione o raccolto
    private final Map<Integer, List<ProdHarvEffect>> prodHarvEffects;

//    private ResourceSet immediateRequirement;

    /**
     *
     * @param name nome della carta
     * @param era era della carta
     * @param requirements requisiti della carta, non da pagare ma da soddisfare
     * @param immediate effetti immediati della carta
     * @param idColour colore della carta
     */
    public DevelopmentCard(String name, int era, List<Requirement> requirements, List<Effect> immediate, String idColour, Map<Integer, List<ProdHarvEffect>> prodHarvEffects)
    {
        super(name);
        if (/*requirements==null ||*/ immediate==null || idColour==null)
            throw new NullPointerException();
        this.immediateEffects = immediate;
        this.era = era;
        this.requirements = requirements;
        this.idColour = idColour;
        this.prodHarvEffects = prodHarvEffects;
    }

    public List<ProdHarvEffect> getProdHarvEffects (int value)
    {
        List<ProdHarvEffect> resultEffects = new LinkedList<>();

        for (int i=0; i<=value; i++) {
            if (prodHarvEffects.get(i) != null) {
                resultEffects.addAll(this.prodHarvEffects.get(i));
            }
        }
        return Collections.unmodifiableList(resultEffects);
    }

    /**
     * Check if is satisfied at least one requirement of the card (useful when if you want to take it)
     * @param
     * @return
     */
    /*
    public boolean isSatisfied(ResourceSet resourceSet)
    {
        boolean result = true;
        for (Requirement requirement:requirements)
        {
            result = false;
            if (requirement.isSatisfied(resourceSet))
                return true;
        }
        return result;
    }
    */
    public int getEra() {
        return era;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public List<Effect> getImmediateEffects() {
        return immediateEffects;
    }

    public String getIdColour() {
        return idColour;
    }

    public Map<Integer, List<ProdHarvEffect>> getProdHarvEffects() {
        return prodHarvEffects;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }
}
