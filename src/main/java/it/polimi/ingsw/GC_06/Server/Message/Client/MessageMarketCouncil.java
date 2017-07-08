package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.BoardActionOnMarketCouncil;
import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 27/06/17.
 */
public class MessageMarketCouncil implements MessageMultipleSteps {

    /** questo messaggio mi deve creare o l'azione market o l'azione council */

    private String player;
    private int game;
    private int familyMember;
    private final int slotSelector;
    private final int slot;
    private int powerUp;
    private ActionType actionType;
    private MarketAndCouncil marketAndCouncil;

    public MessageMarketCouncil(int slotSelector, int slot, int familyMember, int powerUp, ActionType actionType)
    {
        this.slot = slot;
        this.familyMember = familyMember;
        this.slotSelector = slotSelector;
        this.powerUp = powerUp;
        this.actionType = actionType;
    }

    public MessageMarketCouncil(Object slotSelector, int slot)
    {
        this.slotSelector = (int) slotSelector;
        this.slot = slot;
        this.familyMember = -1;
    }

    @Override
    public void execute()
    {
        Game currentGame = GameList.getInstance().getGameId(game);

        // questa è da sistemare

        // a seconda del tipo di azione che ha richiesto il client noi dovremmo aggiungere al consiglio o al mercato

        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);

        if(actionType.equals(ActionType.BOARD_ACTION_ON_MARKET)){
              this.marketAndCouncil = currentGame.getBoard().getMarket().get(slotSelector);
        }
        else
            this.marketAndCouncil = currentGame.getBoard().getCouncils().get(slotSelector);

            BoardActionOnMarketCouncil boardActionOnMarketCouncil = new BoardActionOnMarketCouncil(marketAndCouncil, slot, currentPlayer.getFamilyMembers()[familyMember], currentPlayer, actionType,currentGame, powerUp);


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
    public void setFamilyMember(int index) {
        this.familyMember = index;
    }

    @Override
    public void setPowerUp(int powerUp) {
        this.powerUp = powerUp;
    }
}
