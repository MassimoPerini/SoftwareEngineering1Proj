package it.polimi.ingsw.GC_06.Server.Message.Client;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.DiscardHeroAction;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 7/8/17.
 */
public class DiscardHeroCardMessage implements MessageClient {


    private int game;
    private String player;
    private int discardHeroCard;
    private ActionType actionType;

    public DiscardHeroCardMessage(int discardHeroCard) {
        this.discardHeroCard = discardHeroCard;
        this.actionType = actionType;
    }

    @Override
    public void execute() {
        Game currentGame = GameList.getInstance().getGameId(game);
        Player currentPlayer = currentGame.getGameStatus().getPlayers().get(player);

        Action discardHeroAction = new DiscardHeroAction(currentGame,discardHeroCard,actionType,currentPlayer);

        try{
                discardHeroAction.execute();
            }
            catch(Exception e){
            return;
        }

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
        return null;
    }



    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
