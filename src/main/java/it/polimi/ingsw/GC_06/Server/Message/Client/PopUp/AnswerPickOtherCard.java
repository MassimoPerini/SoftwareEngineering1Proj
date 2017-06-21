package it.polimi.ingsw.GC_06.Server.Message.Client.PopUp;

import it.polimi.ingsw.GC_06.Server.Message.MessageClient;
import it.polimi.ingsw.GC_06.Server.Network.GameList;
import it.polimi.ingsw.GC_06.model.Action.PickCard.PayCard;
import it.polimi.ingsw.GC_06.model.State.Game;

/**
 * Created by massimo on 21/06/17.
 */
public class AnswerPickOtherCard implements MessageClient {

    private int game;
    private String player;
    private final int floor;
    private final String tower;

    public AnswerPickOtherCard(int floor, String tower) {
        this.floor = floor;
        this.tower = tower;
    }


    @Override
    public void execute() {
        Game game = GameList.getInstance().getGameId(this.game);
        PayCard payCard = new PayCard(game.getBoard().getTowers().get(tower), floor, game.getGameStatus().getPlayers().get(player), game);
        if (payCard.isAllowed()) {
            payCard.execute();
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
}
