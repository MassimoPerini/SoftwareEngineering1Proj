package it.polimi.ingsw.GC_06.model.playerTools;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.MessageAddCard;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;

import java.util.*;


/**
 * @author giuseppe
 */
public class PlayerBoard {

    /**
     * This is the object which describes the "plancia"
     */

    private final Map<String,List<PlayerBoardSlot>> cards;

    public PlayerBoard (Map<String,List<PlayerBoardSlot>> cards)
    {
        this.cards = cards;
    }


    // adesso qui mettiamo il metodo per farci restituire un array di carte che vogliamo


    public List<DevelopmentCard> getDevelopmentCards(String color)
    {
        LinkedList <DevelopmentCard> list = new LinkedList();
        for (PlayerBoardSlot playerBoardSlot : cards.get(color)) {
            list.add(playerBoardSlot.getDevelopmentCard());
        }
        return Collections.unmodifiableList(list);
    }

    public List<DevelopmentCard> getDevelopmentCards()
    {
        List list = new ArrayList();
        list.addAll(cards.values());
        return Collections.unmodifiableList(list);
    }

    /**
     * Adds a card to the player board. If it is not possible, generate an IllegalStateException
     * @param card
     */
     void addCard(DevelopmentCard card, ResourceSet resourceSet)
    {
        if (!canAdd(card, resourceSet))
            throw new IllegalStateException();
        PlayerBoardSlot slot = this.getFirstEmpty(card.getIdColour());
        slot.addCard(card, resourceSet);

    }

    private PlayerBoardSlot getFirstEmpty(String color)
    {
        List<PlayerBoardSlot> cardsColor = cards.get(color);
        if (cardsColor == null)
            return null;

        for (PlayerBoardSlot playerBoardSlot : cardsColor) {
            if (playerBoardSlot.isEmpty())
                return playerBoardSlot;
        }
        return null;
    }

    /**
     * check if I can add a card to the player board (check on the restrictions).
     * @param cardId
     * @return
     */
     boolean canAdd (DevelopmentCard cardId, ResourceSet resourceSet)
    {
        PlayerBoardSlot slot = getFirstEmpty(cardId.getIdColour());
        if (slot==null)
            return false;
        return slot.isAllowed(cardId, resourceSet);
    }

    public boolean isIncluded(HashMap<String,Integer> requirements){

        Set<String> colourRequirements = requirements.keySet();
        for(String colour : colourRequirements){
            if(requirements.get(colour) < this.cards.get(colour).size()){
                return false;
            }
        }
        return true;

    }

    /** ritorna il numero di carte di un certo colore possedute da un giocatore*/

}
