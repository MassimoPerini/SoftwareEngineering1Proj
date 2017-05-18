package it.polimi.ingsw.GC_06.Card;

import it.polimi.ingsw.GC_06.Resource.ResourceSet;

/**
 * Created by massimo on 12/05/17.
 */
public class Card {
    private String name;
    private CardType cardType;
//    private ResourceSet requirements;       //requisiti per l'utilizzo


    public Card (String name, CardType cardType)
    {
        this.name = name;
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }
}
