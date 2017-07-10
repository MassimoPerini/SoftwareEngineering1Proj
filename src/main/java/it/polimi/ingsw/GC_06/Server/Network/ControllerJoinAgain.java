package it.polimi.ingsw.GC_06.Server.Network;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.Server.Message.Server.*;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 08/07/17.
 * TODO
 * this class handles messages coming from the player
 */
public class ControllerJoinAgain {

    public void execute(ServerOrchestrator serverOrchestrator, String player, Game game)
    {
        List<MessageServer> messageServerList = new LinkedList<>();
        messageServerList.add(new MessageGameStarted(game));
        for (Player player1 : game.getGameStatus().getPlayers().values()) {
            for (FamilyMember familyMember : player1.getFamilyMembers()) {
                messageServerList.add(new UpdateValueFamilyMember(familyMember.getValue(), familyMember.getDiceColor(), familyMember.getPlayerUserName()));
            }
            messageServerList.add(new MessageUpdateResource(player1.getPLAYER_ID(), player1.getResourceSet()));
        }

        for (Tower tower : game.getBoard().getTowers().values()) {
            messageServerList.add(new MessageNewCardOnTower(tower));
        }

        messageServerList.add(new MessageChangePlayer(game.getCurrentPlayer().getPLAYER_ID(), game.getRoundManager().getEra(), game.getRoundManager().getTurn()));

        for (MessageServer messageServer : messageServerList) {
            serverOrchestrator.notifyUser(player, messageServer);
        }

    }


}
