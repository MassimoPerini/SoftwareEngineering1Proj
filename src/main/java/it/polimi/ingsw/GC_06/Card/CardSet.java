package it.polimi.ingsw.GC_06.Card;


import java.util.LinkedList;

/**
 * Created by massimo on 13/05/17.
 */
public class CardSet {
    private CardType cardsType;
    private LinkedList<Card> cards = new LinkedList<>();


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

    // this is the list made of cards of colour cardsType
    public LinkedList <Card> getColourCard(String cardsID){

        LinkedList<Card> colourList = new LinkedList<Card>();

        for(Card card : cards){

            if((cards.get ()).equals(cardsID)){

                colourList.add(card);
            }
        }

        return colourList;
    }
}
