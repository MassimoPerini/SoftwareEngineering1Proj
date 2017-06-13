package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.ConversionTable;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;

/**
 * Created by giuseppe on 5/31/17.
 */
public class EndTurnAction implements Action {

    public Game game;
    public EndTurnAction() {
        super( );

    }

    @Override
    public void execute() {

        /*
        for(String colour : cardColours ) {

            /**prendo le carte di un certo colore possedute da un giocatore
            int numbOfCards = (player.getPlayerBoard().getColouredCards(colour)).size();

            /**prendo i valori di conversione associati a quella tipologia di carte (identificate dal colore)
            ArrayList<Integer> conversionValues = conversionTable.getConversionTable().get(colour);

            /** se ho meno valori di conversione che carte. Il mio numero di carte sarà uguale
             * alla dimensione dell'array di concersione


            if(numbOfCards > conversionValues.size()){
                numbOfCards = conversionValues.size();
            }
            /** uso numbOfCards come indice per individuare la quantità di punti bonus che riceverò su una certa risorsa
            int amount = conversionValues.get(numbOfCards);
            player.getResourceSet().variateResource(resource,amount);

        }*/

        game.endTurn();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
