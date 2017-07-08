package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Board.TowerFloor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 15/06/17.
 */
public class MessageNewCardOnTower implements MessageServer {

    private final List<String> cards;
    private final String tower;

    public MessageNewCardOnTower(Tower realTower)
    {
        this.tower = realTower.getColor();
        this.cards = new LinkedList<>();

        for (TowerFloor towerFloor : realTower.getTowerFloor()) {
            this.cards.add(towerFloor.getCard().getPath());
        }
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getClientBoardGame().setNewTowerCards(tower, cards);
    }
}
