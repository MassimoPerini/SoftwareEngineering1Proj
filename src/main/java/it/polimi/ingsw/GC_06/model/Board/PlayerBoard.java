package it.polimi.ingsw.GC_06.model.Board;

import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author giuseppe
 */
public class PlayerBoard {

    /**
     * This is the object which describes the "plancia"
     */

    private HashMap<String,ArrayList<DevelopmentCard>> cards;
    private HashMap<String, Integer> maxCards;      //Il "limite" delle carte coltivazione

    public PlayerBoard ()
    {
        this.cards = new HashMap<>();
        this.maxCards = new HashMap<>();        //TODO da caricare da file + (observer?)
    }


    // adesso qui mettiamo il metodo per farci restituire un array di carte che vogliamo

    public ArrayList<DevelopmentCard> getColouredCards(String colour){
        ArrayList<DevelopmentCard> res = cards.get(colour);
        if (res==null)
            return new ArrayList<>();
        return res;
    }

    /**
     * Adds a card to the player board. If it is not possible, generate an IllegalStateException
     * @param card
     */
    public void addCard(DevelopmentCard card)
    {
        if (!canAdd(card))
            throw new IllegalStateException();

        String idCard = card.getIdColour();
        ArrayList<DevelopmentCard> cardsColor = cards.get(idCard);
        if (cardsColor == null)
        {
            cardsColor = new ArrayList<>();
            cardsColor.add(card);
        }
        cardsColor.add(card);
    }

    /**
     * check if I can add a card to the player board (check on the restrictions).
     * @param cardId
     * @return
     */
    public boolean canAdd (DevelopmentCard cardId)
    {
        Integer limit = maxCards.get(cardId.getIdColour());
        ArrayList<DevelopmentCard> cardsKey = cards.get(cardId.getIdColour());
        if (cardsKey == null)
        {
            return limit.intValue() > 0;
        }
        return limit.intValue() > cardsKey.size();
    }

    /** ritorna il numero di carte di un certo colore possedute da un giocatore*/

}
