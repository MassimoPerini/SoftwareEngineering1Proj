package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Action.ProdHarv.DefaultAskUserSelector;
import it.polimi.ingsw.GC_06.model.Action.ProdHarv.StartProdHarv;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 23/06/17.
 * la classe è un effetto che dà un azione di raccolto o produzione aggiuntiva
 */

public class DonateProdHarv implements Effect {

    private int value;
    private ActionType actionType;

    /**
     *
     * @param value il valore dell'azione aggiuntiva da eseguire
     * @param actionType il tipo di azione aggiunta, raccolto o produzione
     */
    public DonateProdHarv(int value, ActionType actionType) {
        this.value = value;
        this.actionType = actionType;
    }

    @Override
    public void execute(Player player, Game game) throws InterruptedException {
        PowerUp powerUpAction = new PowerUp();
        value += powerUpAction.execute(game, player);

        StartProdHarv startProdHarv = new StartProdHarv( actionType, new DefaultAskUserSelector(), value, player, game);
        if (startProdHarv.isAllowed())
            startProdHarv.execute();

    }
}
