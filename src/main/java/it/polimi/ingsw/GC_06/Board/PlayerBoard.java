package it.polimi.ingsw.GC_06.Board;

import it.polimi.ingsw.GC_06.Card.DevelopmentCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by giuseppe on 5/20/17.
 */
public class PlayerBoard {

    /**
     * This is the object which describes the "plancia"
     */

   private HashMap<String,DevelopmentCard> cards = new HashMap<String,DevelopmentCard>();

    public HashMap<String, DevelopmentCard> getCards() {
        return cards;
    }

    public void setCards(HashMap<String, DevelopmentCard> cards) {
        this.cards = cards;
    }



    // adesso qui mettiamo il metodo per farci restituire un array di carte che vogliamo

    public LinkedList<DevelopmentCard> getColouredCards(String colour){

        Iterator it = cards.entrySet().iterator();
        LinkedList<DevelopmentCard> colouredCards = new LinkedList<DevelopmentCard>();
        while (it.hasNext()){

            if(cards.keySet().equals(colour)){
                /**
                 * non sapevo il nome del metodo per farmi restituire il valore dalla hash map
                 * comunque eccetto questo funziona
                 */

                colouredCards.add((DevelopmentCard) cards.values());
            }
        }

        return colouredCards;

    }
}
