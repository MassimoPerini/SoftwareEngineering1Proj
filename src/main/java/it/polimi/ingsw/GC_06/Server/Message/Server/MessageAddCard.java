package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import org.jetbrains.annotations.NotNull;

/**
 * Created by massimo on 14/06/17.
 */
public class MessageAddCard implements MessageServer {

    private final String developmentCard;
    private final String color;
    private final String username;

    public MessageAddCard(@NotNull DevelopmentCard developmentCard, @NotNull String username)
    {
        this.color = developmentCard.getIdColour();
        this.developmentCard = developmentCard.getPath();
        this.username = username;
    }

    @Override
    public void execute(MainClientModel mainClientModel) {
        ClientPlayerBoard clientPlayerBoard = mainClientModel.getClientPlayerBoard(username);
        clientPlayerBoard.addCard(color, developmentCard);
    }
}
