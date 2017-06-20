package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvMalusEffect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 05/06/17.
 */
public class StartProdHarv implements Action {

    private final List<DevelopmentCard> developmentCards;
    private final AskUserCard prodHarvFilterCard;
    private final int value;
    private final Player player;
    private Game game;
    private ActionType actionType;

    /**
     *
     * @param cardList List of cards
     * @param askUserCardFilter The functions that choose the cards we need to ask
     * @param value The value of the production/harvest
     * @param player The player that started the Action
     */

    public StartProdHarv(List<DevelopmentCard> cardList, ActionType actionType, AskUserCard askUserCardFilter, int value, Player player)   //It should check if there is at least a "resource transformation" effect
    {
        super();
        if (cardList==null || askUserCardFilter==null)
            throw new NullPointerException();

        this.developmentCards = new LinkedList<>();

        for (DevelopmentCard developmentCard : cardList)
        {
            if (askUser(developmentCard,value))
            {
                this.developmentCards.add(developmentCard);
            }
        }

        this.prodHarvFilterCard = askUserCardFilter;
        this.value = value;
        this.player = player;
        this.actionType = actionType;
    }

    /**
     * Starts a production/harvest
     */
    @Override
    public void execute() {
        game.getGameStatus().changeState(TransitionType.START_PRODHARV);


        List<ProdHarvEffect> autoExecute = new LinkedList<>();
        List<DevelopmentCard> askUser = new LinkedList<>();

        //Select the cards we need to ask

        for (DevelopmentCard developmentCard: player.getPlayerBoard().getDevelopmentCards())
        {
            if (prodHarvFilterCard.askUser(developmentCard, value))     //I need to ask
            {
                askUser.add(developmentCard);
            }
            else {             //Otherwise if it is allowed I will execute it
                List<ProdHarvEffect> effects = developmentCard.getProdHarvEffects(value);
                for (ProdHarvEffect effect : effects) {
                    if (effect.isAllowed(player)){
                        autoExecute.add(effect);
                    }
                }

            }
        }

        //Apply the auto-maluses

        List <Effect> temp = new LinkedList<>();
        for (ProdHarvEffect effect : autoExecute)
        {
            for (ProdHarvMalusEffect malusEffect : effect.getMalusEffect()) {
                if (malusEffect.isAllowed(player))
                {
                    temp.add(malusEffect);          //I have to execute the malus because it is not optional
                }
            }
            temp.addAll(effect.getMalusEffect());
        }

        ExecuteEffects executeEffects = new ExecuteEffects(temp, player,game);
        if (executeEffects.isAllowed())
        {
            executeEffects.execute();
        }

        //Ask the cards

        if (askUser.size()>0)
        {
            //stiamo mandando le carte fra cui scegliere allo stato che poi le manderà al controller e poi al client
            game.getGameStatus().changeState(TransitionType.START_PRODHARV, askUser);
        }

        //Apply the auto-bonus
        temp = new LinkedList<>();
        for (ProdHarvEffect effect : autoExecute)
        {
            temp.addAll(effect.getBonusEffect());
        }
        executeEffects = new ExecuteEffects(temp, player,game);
        if (executeEffects.isAllowed())
        {
            executeEffects.execute();
        }

    }

    public boolean askUser(DevelopmentCard card, int points) {
        List<ProdHarvEffect> effects = card.getProdHarvEffects(points);
        for (ProdHarvEffect effect : effects)
        {
            if (effect.getMalusEffect().size() > 0)
            {
                return true;
            }
        }
        return false;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
