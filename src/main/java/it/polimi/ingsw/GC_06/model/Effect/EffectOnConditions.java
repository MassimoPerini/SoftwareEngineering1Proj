package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 5/28/17.
 * la classe è un effetto che permette di convertire tra di loro alcune carte con risorse e punti sulla base di
 * condizioni settate
 */
public class EffectOnConditions implements Effect{

    private Resource conditionalResource; /** può essere una carta o una Resource */
    private String cardColour;
    private double multiplier;
    private Resource trasferredResource;

    public EffectOnConditions(Resource conditionalResource, double multiplier, Resource trasferredResource,String cardColour) {
        this.conditionalResource = conditionalResource;
        this.multiplier = multiplier;
        this.trasferredResource = trasferredResource;
        this.cardColour = cardColour;
    }

    /**
     *
     * @param player il giocatore sul quale eseguire l'effetto
     * @param game il gioco corrente
     */
    @Override
    public void execute(Player player,Game game) {

       int conditionalValue =  converter(player);

       int transferValue = (int) (conditionalValue * multiplier);

       ResourceSet resourceSet = new ResourceSet();
       resourceSet.variateResource(trasferredResource,transferValue);
       player.variateResource(resourceSet);
    }

    /**
     *
     * @param player il giocatore sul quale eseguire l'effetto
     * @return ritorna la quantità di carte del giocatore che rispettano il colore settato per la conversione
     */
    private int converter(Player player){

        int conditionalValue;

        if(this.cardColour == null && conditionalResource == null){
            throw new IllegalStateException();
        }
        if(this.cardColour == null){
            return player.getResourceSet().getResourceAmount(conditionalResource);
        }
        else
            return player.getPlayerBoard().getDevelopmentCards(cardColour).size();

    }

}
