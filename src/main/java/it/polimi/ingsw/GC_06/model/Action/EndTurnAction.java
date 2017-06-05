package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.playerTools.ConversionTable;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/31/17.
 */
public class EndTurnAction extends ActionBoh {

    private Player player;
    private ArrayList<String> cardColours;
    private ConversionTable conversionTable;
    /** questa cosa è assolutamente da cambiare, si può fare molto meglio*/
    private Resource resource;

    public EndTurnAction(Player player, ArrayList<String> cardColours, ConversionTable conversionTable, Resource resource) {
        super( 1);
        this.player = player;
        this.cardColours = new ArrayList<String>();
        this.conversionTable = conversionTable;
        this.resource = resource;
    }

    @Override
    public void execute() {


        for(String colour : cardColours ) {

            /**prendo le carte di un certo colore possedute da un giocatore*/
            int numbOfCards = (player.getPlayerBoard().getColouredCards(colour)).size();

            /**prendo i valori di conversione associati a quella tipologia di carte (identificate dal colore)*/
            ArrayList<Integer> conversionValues = conversionTable.getConversionTable().get(colour);

            /** se ho meno valori di conversione che carte. Il mio numero di carte sarà uguale
             * alla dimensione dell'array di concersione
             */

            if(numbOfCards > conversionValues.size()){
                numbOfCards = conversionValues.size();
            }
            /** uso numbOfCards come indice per individuare la quantità di punti bonus che riceverò su una certa risorsa*/
            int amount = conversionValues.get(numbOfCards);
            player.getResourceSet().variateResource(resource,amount);

        }
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
