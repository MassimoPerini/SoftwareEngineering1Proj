package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.BoardActionOnMarketCouncil;
import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 27/06/17.
 */
public class MessageMarketCouncil implements MessageClient {

    private String player;
    private int game;
    private final int familyMember;
    private final int marketCouncil;
    private final int slot;

    public MessageMarketCouncil(int marketCouncil, int slot, int familyMember)
    {
        this.slot = slot;
        this.familyMember = familyMember;
        this.marketCouncil = marketCouncil;
    }


    @Override
    public void execute()
    {
        Game currentGame = GameList.getInstance().getGameId(game);
        MarketAndCouncil marketAndCouncil = currentGame.getBoard().getMarketAndCouncils().get(marketCouncil);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);

        BoardActionOnMarketCouncil boardActionOnMarketCouncil = new BoardActionOnMarketCouncil(marketAndCouncil, slot, currentPlayer.getFamilyMembers()[familyMember], currentPlayer, marketAndCouncil.getActionType());

        try {
            if (boardActionOnMarketCouncil.isAllowed()) {
                boardActionOnMarketCouncil.execute();
            }
        }
        catch (InterruptedException e){}
    }

    @Override
    public void setGame(int game) {
        this.game = game;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public void run() {
        execute();
    }
}
