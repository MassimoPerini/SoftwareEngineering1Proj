package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Client.Model.*;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;

/**
 * Created by massimo on 18/06/17.
 */
public class MessageChangePlayer implements MessageServer {
    private String newPlayer;
    private int era;
    private int turn;

    public MessageChangePlayer(String newPlayer, int era, int turn) {
        this.newPlayer = newPlayer;
        this.era = era;
        this.turn = turn;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().updateStatus(turn, era, newPlayer);

        //CANCELLARE!!!!!

        clientController.getViewOrchestrator().change(ClientStateName.GAME_START, "");





    }
}
