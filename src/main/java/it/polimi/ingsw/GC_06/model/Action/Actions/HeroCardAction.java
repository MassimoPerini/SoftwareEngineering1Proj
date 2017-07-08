package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by giuseppe on 6/11/17.
 * la classe implementa la possibilità, durante un turno, di giocare una carta eroe
 */
public class HeroCardAction implements Action {

    private Player player;
    private List<Integer> desiredHeroCardIndexHeroCard;
    private ActionType actionType;
    private Game game;

    public HeroCardAction(Game game,Player player, List<Integer> desiredHeroCardIndexHeroCard, ActionType actionType) {
        this.player = player;
        this.desiredHeroCardIndexHeroCard = desiredHeroCardIndexHeroCard;
        this.actionType = actionType;
        this.game = game;
    }

    /**
     * il metodo che si occupa di lanciare gli effetti della carta scelta dal giocatore
     * @throws InterruptedException
     */
    @Override
    public void execute() throws InterruptedException {


        game.getGameStatus().changeState(TransitionType.PLAY_HERO_CARD);
        List<HeroCard> heroCardList = player.getHeroCard();

        for (Integer index : desiredHeroCardIndexHeroCard) {
            List <Effect> heroCardEffectList = heroCardList.get(index).getEffectList();

            for (Effect effect : heroCardEffectList) {
                effect.execute(player,game);
            }
        }

        game.getGameStatus().changeState(TransitionType.END_ACTION);
    }

    @Override
    public boolean isAllowed() {

        for (Integer index : desiredHeroCardIndexHeroCard) {
            if(!player.getHeroCard().get(index).isActivable(player)){
                return false;
            }

        }

        return true;

    }
}


