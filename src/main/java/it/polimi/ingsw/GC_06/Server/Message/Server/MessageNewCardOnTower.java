package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 15/06/17.
 */
public class MessageNewCardOnTower implements MessageServer {

    private final List<String> cards;
    private final String tower;

    public MessageNewCardOnTower(List<DevelopmentCard> cards, String tower)
    {
        this.tower = tower;
        this.cards = new LinkedList<>();
        for (DevelopmentCard card : cards) {
            this.cards.add(card.getPath());
        }
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getClientBoardGame().setNewTowerCards(tower, cards);
    }
}
