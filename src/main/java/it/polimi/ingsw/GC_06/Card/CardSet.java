package it.polimi.ingsw.GC_06.Card;


import java.util.LinkedList;

/**
 * Created by massimo on 13/05/17.
 */
public class CardSet {
    CardType cardsType;
    LinkedList<Card> cards = new LinkedList<>();

    public CardSet(CardType cardType){
        this.cardsType = cardType;
    }

    public void addCard (Card card)
    {
        if (card.getCardType() != cardsType){
            throw new IllegalStateException();
        }

        cards.add(card);
    }

    public CardType getCardsType() {
        return cardsType;
    }
}
