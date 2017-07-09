package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;

import java.util.List;

/**
 * Created by giuseppe on 7/8/17.
 */
public class HeroCardUploadMessageServer implements MessageServer {

    private String player;
    private List<String> heroCards;

    public HeroCardUploadMessageServer(String player, List<String> heroCards) {
        this.player = player;
        this.heroCards = heroCards;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getClientPlayerBoard(player).setHeroCards(heroCards);
    }
}
