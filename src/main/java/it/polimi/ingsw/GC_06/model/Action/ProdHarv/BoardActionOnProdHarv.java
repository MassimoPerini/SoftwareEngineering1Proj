package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.BonusMalus.BonusMalusHandler;
import it.polimi.ingsw.GC_06.model.playerTools.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Board.ProdHarvZone;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;


/**
 * Created by massimo on 01/06/17.
 */
public class BoardActionOnProdHarv implements Action {

    private final int index;
    private final Player player;
    private final ProdHarvZone prodHarvArea;
    private final FamilyMember familyMember;
    private final StartProdHarv startProdHarv;
    private Game game;
    private ActionType actionType;

    /**
     *
     * @param player The player that invoked the action
     * @param index Index of the field of the production/harvest area
     * @param prodHarvArea The production/harvest area
     * @param selectProdHarvCard The function that selects the production/harvest cards
     * @param askUserCardFilter The function that selects the cards that we have to ask
     * @param familyMember The family member placed
     */

    public BoardActionOnProdHarv(Player player, int index, ProdHarvZone prodHarvArea, ProdHarvFilterCard selectProdHarvCard, AskUserCard askUserCardFilter , FamilyMember familyMember,ActionType actionType)
    {
        super();
        if (player == null || prodHarvArea == null || familyMember == null || askUserCardFilter==null || selectProdHarvCard == null)
            throw new NullPointerException();

        this.prodHarvArea = prodHarvArea;
        this.actionType = actionType;
        this.player = player;
        this.index = index;
        this.familyMember = familyMember;
        this.startProdHarv = new StartProdHarv(player.getPlayerBoard().getDevelopmentCards(), selectProdHarvCard, askUserCardFilter ,familyMember.getValue(), player);
    }

    /**
     * Adds the family member and starts the prod/harv
     */

    @Override
    public void execute() {

        game.getGameStatus().changeState(TransitionType.ACTION_ON_PRODHARV);

        BonusMalusHandler.filter(player,actionType,familyMember);

        if (!isAllowed())
            throw new IllegalStateException();

        prodHarvArea.addFamilyMember(familyMember, index);
        List<Effect> effects = prodHarvArea.getEffect(index);
        for (Effect effect : effects)
        {
            effect.execute(player,game);
        }

        startProdHarv.execute();

    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean isAllowed() {
        return !prodHarvArea.isAllowed(familyMember, index) && startProdHarv.isAllowed();
    }
}
