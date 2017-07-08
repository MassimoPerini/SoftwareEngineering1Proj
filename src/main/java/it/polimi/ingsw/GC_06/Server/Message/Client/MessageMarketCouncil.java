package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.BoardActionOnMarketCouncil;
import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 27/06/17.
 */
public class MessageMarketCouncil implements MessageMultipleSteps {

    private String player;
    private int game;
    private int familyMember;
    private final int marketCouncil;
    private final int slot;
    private int powerUp;

    public MessageMarketCouncil(int marketCouncil, int slot, int familyMember, int powerUp)
    {
        this.slot = slot;
        this.familyMember = familyMember;
        this.marketCouncil = marketCouncil;
        this.powerUp = powerUp;
    }

    public MessageMarketCouncil(Object marketCouncil, int slot)
    {
        this.marketCouncil = (int) marketCouncil;
        this.slot = slot;
        this.familyMember = -1;
    }

    @Override
    public void execute()
    {
        Game currentGame = GameList.getInstance().getGameId(game);
        MarketAndCouncil marketAndCouncil = currentGame.getBoard().getMarketAndCouncils().get(marketCouncil);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);

        BoardActionOnMarketCouncil boardActionOnMarketCouncil = new BoardActionOnMarketCouncil(marketAndCouncil, slot, currentPlayer.getFamilyMembers()[familyMember], currentPlayer, marketAndCouncil.getActionType(),currentGame, powerUp);

        try {
            if (boardActionOnMarketCouncil.isAllowed()) {
                boardActionOnMarketCouncil.execute();
            }
            else{
                currentGame.getGameStatus().changeState(TransitionType.ERROR);
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
    public boolean isValid() {
        return familyMember!=-1;
    }

    @Override
    public void run() {
        execute();
    }

    @Override
    public void setFamilyMember(int index) {
        this.familyMember = index;
    }

    @Override
    public void setPowerUp(int powerUp) {
        this.powerUp = powerUp;
    }
}
