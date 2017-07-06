package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 06/06/17.
 */
public class BoardActionOnMarketCouncil implements Action {

    private final MarketAndCouncil marketAndCouncil;
    private final int index;
    private final FamilyMember familyMember;
    private final ExecuteEffects executeEffects;
    private Player player;
    private Game game;
    private ActionType actionType;
    private final int powerUp;

    public BoardActionOnMarketCouncil(MarketAndCouncil marketAndCouncil, int index, FamilyMember familyMember, Player player,ActionType actionType,Game game, int powerUp)
    {
        super();
        this.player = player;
        this.actionType = actionType;
        if (marketAndCouncil==null || familyMember==null || player==null) {
            throw new NullPointerException();
        }
        this.game = game;
        this.marketAndCouncil = marketAndCouncil;
        this.index = index;
        this.familyMember = familyMember;
        List<Effect> effectList = marketAndCouncil.getEffects(index);
        this.powerUp = powerUp;
        if (effectList==null)
            throw new NullPointerException();

        this.executeEffects = new ExecuteEffects(effectList, player,game);
    }

    @Override
    public void execute() throws InterruptedException {

        familyMember.useIt();

        BonusMalusHandler.filter(player,actionType,null,familyMember);

        game.getGameStatus().changeState(TransitionType.ACTION_ON_MARKETCOUNSIL);

        marketAndCouncil.addFamilyMember(familyMember, index);
        if (executeEffects.isAllowed()) {
            executeEffects.execute();
        }

        game.getGameStatus().changeState(TransitionType.END_ACTION);
        player.getBonusMalusSet().removeBonusMalusAction(actionType,null);

    }


    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {
        if (!familyMember.isAllowed())
        {
            return false;
        }
        int originalValue = familyMember.getValue();
        BonusMalusHandler.filter(player,actionType,null,familyMember);
        boolean value = marketAndCouncil.isAllowed(familyMember, index) && executeEffects.isAllowed() && game.getGameStatus().getCurrentStatus().canConsume(TransitionType.ACTION_ON_MARKETCOUNSIL);
        familyMember.setValue(originalValue);
        return BonusMalusHandler.filter(player,actionType,value);
    }


/*
//TODO BONUSMALUS
    public void run() {
        boolean position = this.isAllowed();
        if (BonusMalusHandler.filter(player,actionType,position)){
            execute();
            player.getBonusMalusSet().removeBonusMalusAccess(actionType,position);
        }
        else{
            throw new IllegalStateException();
        }
    }

    */
}
