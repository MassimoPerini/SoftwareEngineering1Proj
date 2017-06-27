package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by giuseppe on 6/11/17.
 */
public class HeroCardAction implements Action {

    private Player player;
    private List<Integer> desiredHeroCardIndexHeroCard;
    private ActionType actionType;
    private Game game;

    public HeroCardAction(Player player, List<Integer> desiredHeroCardIndexHeroCard, ActionType actionType) {
        this.player = player;
        this.desiredHeroCardIndexHeroCard = desiredHeroCardIndexHeroCard;
        this.actionType = actionType;
        this.game = game;
    }

    @Override
    public void execute() throws InterruptedException {

        List<HeroCard> heroCardList = player.getPlayerBoard().getHeroCards();
        for (HeroCard heroCard : heroCardList) {
            List<Effect> heroCardEffectList= heroCard.getEffectList();
            for (Effect effect : heroCardEffectList) {
                effect.execute(player,game);
            }

            if(!heroCard.isPermanent()){
                heroCard.setCardStatus(false);
            }

        }



    }

    @Override
    public boolean isAllowed() {

        List<HeroCard> heroCardList = player.getPlayerBoard().getHeroCards();
        for (HeroCard heroCard : heroCardList) {
            if(!heroCard.isActivable(player)){
                return false;
            }
        }

        return true;
    }
}


