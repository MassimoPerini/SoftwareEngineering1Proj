package it.polimi.ingsw.GC_06.model.Action.Actions;

import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;

/**
 * Created by massimo on 05/06/17.
 * azione che prende una lista di effetti associati ad un elemento e ne lancia l'esecuzione
 */
public class ExecuteEffects implements Action {

    private final List<Effect> effects;
    private final Player player;
    private Game game;

    public ExecuteEffects(List<Effect> effectList, Player player,Game game) {
        super();
        if (effectList == null)
            throw new NullPointerException();
        this.effects = effectList;
        this.player = player;
        this.game = game;
    }

    /**
     * il metodo cicla su una lista di effetti e li esegue tutti
     * @throws InterruptedException
     */
    @Override
    public void execute() throws InterruptedException {

     //   game.getGameStatus().changeState(TransitionType.EXECUTE_EFFECT);

        for(Effect effect: effects)
        {
            effect.execute(player,game);
        }

    }

    @Override
    public boolean isAllowed() {
        return  true;
    }


}
