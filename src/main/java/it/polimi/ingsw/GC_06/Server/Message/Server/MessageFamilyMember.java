package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by giuseppe on 6/19/17.
 *
 */
public class MessageFamilyMember implements MessageServer{

    private int value;
    private String diceColor;
    private String playerUserName;

    public MessageFamilyMember(int value, String diceColor, String playerUserName) {
        this.value = value;
        this.diceColor = diceColor;
        this.playerUserName = playerUserName;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().getClientPlayerBoard(playerUserName).changeValueFamilyMember(diceColor, value);

        //CANCELLARE!!!!!

        clientController.getViewOrchestrator().change(ClientStateName.GAME_START, "");
    }
}
