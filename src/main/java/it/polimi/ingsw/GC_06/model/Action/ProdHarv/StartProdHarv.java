package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 05/06/17.
 */
public class StartProdHarv implements Action {

    private List<DevelopmentCard> developmentCards;
    private AskUserCard prodHarvFilterCard;
    private int value;
    private Player player;

    /**
     *
     * @param cardList List of cards
     * @param prodHarvFilter The functions that filters the useful cards
     * @param askUserCardFilter The functions that choose the cards we need to ask
     * @param value The value of the production/harvest
     * @param player The player that started the Action
     */

    public StartProdHarv(List<DevelopmentCard> cardList, ProdHarvFilterCard prodHarvFilter, AskUserCard askUserCardFilter, int value, Player player)   //It should check if there is at least a "resource transformation" effect
    {
        super();
        if (cardList==null || askUserCardFilter==null)
            throw new NullPointerException();

        this.developmentCards = new LinkedList<>();

        for (DevelopmentCard developmentCard : cardList)
        {
            if (prodHarvFilter.isSatisfiable(developmentCard))
            {
                this.developmentCards.add(developmentCard);
            }
        }

        this.prodHarvFilterCard = askUserCardFilter;
        this.value = value;
        this.player = player;
    }

    /**
     * Starts a production/harvest
     */
    @Override
    public void execute() {
        Game.getInstance().getGameStatus().changeState(TransitionType.PRODHARV);

        //First filter: value pawn >= required card value


        List<ProdHarvEffect> autoExecute = new LinkedList<>();
        List<DevelopmentCard> askUser = new LinkedList<>();

        //Select the cards we need to ask

        for (DevelopmentCard developmentCard: player.getPlayerBoard().getDevelopmentCards())
        {
            if (prodHarvFilterCard.askUser(developmentCard, value))
            {
                askUser.add(developmentCard);
            }
            else{
                autoExecute.addAll(developmentCard.getProdHarvEffects(value));
            }
        }

        //Apply the auto-maluses

        List <Effect> temp = new LinkedList<>();
        for (ProdHarvEffect effect : autoExecute)
        {
            temp.addAll(effect.getMalusEffect());
        }

        ExecuteEffects executeEffects = new ExecuteEffects(temp, player);
        if (executeEffects.isAllowed())
        {
            executeEffects.execute();
        }

        //Ask the cards

        if (askUser.size()>0)
        {
            Game.getInstance().getGameStatus().changeState(TransitionType.USR_PRODHARVTRANSFORM, askUser);
        }

        //Apply the auto-bonus
        temp = new LinkedList<>();
        for (ProdHarvEffect effect : autoExecute)
        {
            temp.addAll(effect.getBonusEffect());
        }
        executeEffects = new ExecuteEffects(temp, player);
        if (executeEffects.isAllowed())
        {
            executeEffects.execute();
        }

    }

    @Override
    public boolean isAllowed() {
        return true;
    }
}
