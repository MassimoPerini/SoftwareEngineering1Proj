package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.EffectOnParchment;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by giuseppe on 7/8/17.
 */
public class DiscardHeroAction implements Action {


    private int discardedHeroCard;
    private ActionType actionType = ActionType.PLAY_HERO_CARD;
    private Game game;
    private Player player;


    public DiscardHeroAction(Game game,int discardedHeroCard, ActionType actionType, Player player) {
        this.discardedHeroCard = discardedHeroCard;
        this.actionType = actionType;
        this.game = game;
        this.player = player;
    }

    @Override
    public void execute() throws InterruptedException {

        game.getGameStatus().changeState(TransitionType.PLAY_HERO_CARD);

        player.removeHeroCard(discardedHeroCard);
        Effect effect = new EffectOnParchment(1,true);
        effect.execute(player,game);

        game.getGameStatus().changeState(TransitionType.END_ACTION);


    }



    @Override
    public boolean isAllowed() throws InterruptedException {
        return false;
    }
}
