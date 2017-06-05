package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.ProdHarvEffect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 05/06/17.
 */
public class StartProdHarv implements Action {

    private List<DevelopmentCard> developmentCards;
    private DevelopmentCardFilter developmentCardFilter;
    private int value;

    public StartProdHarv(List<DevelopmentCard> cardList, DevelopmentCardFilter askUserCardFilter, int value)   //It should check if there is at least a "resource transformation" effect
    {
        this.developmentCards = cardList;
        this.developmentCardFilter = developmentCardFilter;
        this.value = value;
    }

    @Override
    public void execute() {
        Game.getInstance().getGameStatus().changeState(TransitionType.STARTPRODHARV);

        //First filter: value prod/harvest

        for (DevelopmentCard developmentCard: developmentCards)
        {
         //   developmentCard.get
        }

        List<ProdHarvEffect> autoExecute = new LinkedList<>();
        List<DevelopmentCard> askUser = new LinkedList<>();

        for (DevelopmentCard developmentCard: developmentCards)
        {
            if (developmentCardFilter.isSatisfiable(developmentCard))
            {
                askUser.add(developmentCard);
            }
            else{
             //   autoExecute.addAll(developmentCard.getProdHarvEffects());
            }
        }

        if (askUser.size()>0)
        {
            Game.getInstance().getGameStatus().changeState(TransitionType.USR_PRODHARVTRANSFORM, askUser);
        }

        Game.getInstance().getGameStatus().changeState(TransitionType.END);
    }

    @Override
    public boolean isAllowed() {
        return false;
    }
}
