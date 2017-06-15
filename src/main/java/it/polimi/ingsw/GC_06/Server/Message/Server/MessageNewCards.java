package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 15/06/17.
 */
public class MessageNewCards implements MessageServer {

    private final List<String> cards;
    private final String tower;

    public MessageNewCards(List<DevelopmentCard> cards, String tower)
    {
        this.tower = tower;
        int i=0;
        this.cards = new LinkedList<>();
        cards = new LinkedList<>();
        for (DevelopmentCard card : cards) {
            this.cards.add(cards.get(i).getPath());
            i++;
        }
    }

    @Override
    public void execute(MainClientModel mainClientModel) {
        mainClientModel.getClientBoardGame().setNewTowerCards(tower, cards);
    }
}
