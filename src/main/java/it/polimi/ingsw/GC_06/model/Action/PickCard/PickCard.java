package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

/**
 * Created by massimo on 26/05/17.
 * la classe si occupa di aggiungere una carta al giocatore e di eseguirne gli effetti
 */
public class PickCard implements Action {

    
    private final Player player;
    private final int towerFloor;
    private final Tower tower;
    private final Game game;

    public PickCard(Player player, Tower tower, int towerFloor,Game game)
    {
        super();
        this.player = player;
        this.towerFloor = towerFloor;
        this.tower = tower;
        this.game = game;
    }

    /**
     *
     * @throws InterruptedException
     */
    @Override
    public void execute() throws InterruptedException {


        /**we are adding the card to the player board*/
        DevelopmentCard c = tower.pickCard(towerFloor);
        player.addCard(c);
        ExecuteEffects executeEffects = new ExecuteEffects(c.getImmediateEffects(), player,game);

        executeEffects.execute();

    }

    /**
     *
     * @return ritorna se l'azione può essere eseguita
     */
    @Override
    public boolean isAllowed() {

        /** controllo se il player può aggiungere la carta */
        if (!player.canAdd(tower.getTowerFloor().get(towerFloor).getCard()))
            return false;

        ExecuteEffects executeEffects = new ExecuteEffects( tower.getTowerFloor().get(towerFloor).getCard().getImmediateEffects(), player,game);

        return executeEffects.isAllowed();
    }


}
