package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.HeroCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by giuseppe on 6/20/17.
 */
public class PlayHeroCardAction implements Action {

    private final ActionType ACTION_TYPE = ActionType.PLAY_HERO_CARD;
    private List<HeroCard> heroCards;
    private Player player;
    private Game game;

    public PlayHeroCardAction(List<HeroCard> heroCards, Player player,Game game) {
        this.heroCards = heroCards;
        this.player = player;
        this.game = game;
    }

    @Override
    public void execute() {



            for (HeroCard heroCard : heroCards) {

                if (heroCard.isActivable(player)) {

                    List<Effect> heroCardEffects = heroCard.getEffectList();

                    for (Effect heroCardEffect : heroCardEffects) {
                        heroCardEffect.execute(player, game);
                        if (heroCard.getCardType().equals("ONCE_PER_TURN"))
                            heroCard.setCardStatus(false);
                    }

                }
            }

    }

    @Override
    public boolean isAllowed() {
        return false;
    }

}
