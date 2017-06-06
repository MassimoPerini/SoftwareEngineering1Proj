package it.polimi.ingsw.GC_06.model.Action;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Board.MarketAndCouncil;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.Collections;
import java.util.List;

/**
 * Created by massimo on 06/06/17.
 */
public class BoardActionOnMarketCouncil implements Action {

    private final MarketAndCouncil marketAndCouncil;
    private final int index;
    private final FamilyMember familyMember;
    private final ExecuteEffects executeEffects;

    public BoardActionOnMarketCouncil(MarketAndCouncil marketAndCouncil, int index, FamilyMember familyMember, Player player)
    {
        super();
        if (marketAndCouncil==null || familyMember==null || player==null)
            throw new NullPointerException();

        this.marketAndCouncil = marketAndCouncil;
        this.index = index;
        this.familyMember = familyMember;
        List<Effect> effectList = marketAndCouncil.getEffects(index);

        if (effectList==null)
            throw new NullPointerException();

        this.executeEffects = new ExecuteEffects(effectList, player);
    }

    @Override
    public void execute() {

        marketAndCouncil.addFamilyMember(familyMember, index);
        executeEffects.execute();

    }

    @Override
    public boolean isAllowed() {
        return marketAndCouncil.isAllowed(familyMember, index) && executeEffects.isAllowed();
    }
}
