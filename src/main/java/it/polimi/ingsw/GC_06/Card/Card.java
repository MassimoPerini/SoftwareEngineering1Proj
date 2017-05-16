package it.polimi.ingsw.GC_06.Card;

/**
 * Created by massimo on 12/05/17.
 */
public class Card {
    private String name;
    private CardType cardType;


    public Card (String name, CardType cardType)
    {
        this.name = name;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }
}
