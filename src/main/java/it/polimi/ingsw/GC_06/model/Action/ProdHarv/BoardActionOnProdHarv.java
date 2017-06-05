package it.polimi.ingsw.GC_06.model.Action.ProdHarv;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.Action;
import it.polimi.ingsw.GC_06.model.Action.ActionBoh;
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

    private int index;
    private Player player;
    private ProdHarvZone prodHarvArea;
    private FamilyMember familyMember;
    private StartProdHarv startProdHarv;

    public BoardActionOnProdHarv(Player player, int index, ProdHarvZone prodHarvArea, FamilyMember familyMember)
    {
        super();
        if (player == null || prodHarvArea == null || familyMember == null)
            throw new NullPointerException();

        this.prodHarvArea = prodHarvArea;
        this.player = player;
        this.index = index;
        this.familyMember = familyMember;
    }

    @Override
    public void execute() {

        Game.getInstance().getGameStatus().changeState(TransitionType.ADDFAMILYMEMBER);

        if (!isAllowed())
            throw new IllegalStateException();

        prodHarvArea.addFamilyMember(familyMember, index);
        List<Effect> effects = prodHarvArea.getEffect(index);
        for (Effect effect : effects)
        {
            effect.execute(player);
        }

        Game.getInstance().getGameStatus().changeState(TransitionType.END);
    }

    @Override
    public boolean isAllowed() {
        return prodHarvArea.isAllowed(familyMember, index);
    }
}
