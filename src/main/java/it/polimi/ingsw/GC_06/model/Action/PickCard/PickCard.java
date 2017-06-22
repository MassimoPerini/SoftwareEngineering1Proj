package it.polimi.ingsw.GC_06.model.Action.PickCard;

import it.polimi.ingsw.GC_06.model.Action.Actions.Action;
import it.polimi.ingsw.GC_06.model.Action.Actions.ExecuteEffects;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Effect.Effect;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.playerTools.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by massimo on 26/05/17.
 */
public class PickCard implements Action {

    
    private final Player player;
    private final int towerFloor;
    private final Tower tower;
    private final Game game;

    public PickCard(@NotNull Player player, @NotNull Tower tower, int towerFloor,Game game)
    {
        super();
        this.player = player;
        this.towerFloor = towerFloor;
        this.tower = tower;
        this.game = game;
    }

    @Override
    public void execute() {


        /**if we are in the real action we add the family member in the correct position*/
        //Tower penality
        if (!tower.isNoPenalityAllowed()) {
            ResourceSet malusResources = tower.getMalusOnMultipleFamilyMembers();
            player.variateResource(malusResources);
            //TODO INSERIAMO QUA LA CHIAMATA A FILTER CHE CI DIRÀ SE NON DOBBIAMO PAGARE PIÙ
        }

        //Apply ActionSpace effects
        List<Effect> effects = tower.getTowerFloor().get(towerFloor).getActionPlace().getEffects();
        ExecuteEffects executeEffects = new ExecuteEffects(effects, player,game);
        executeEffects.execute();

        /**we are adding the card to the player board*/
        DevelopmentCard c = tower.pickCard(towerFloor);
        player.addCard(c);
        executeEffects = new ExecuteEffects(c.getImmediateEffects(), player,game);

    //    game.getGameStatus().changeState(TransitionType.PICK_CARD);

        executeEffects.execute();

    }

    @Override
    public boolean isAllowed() {

        /** controllo se il player può aggiungere la carta */
        if (!player.canAdd(tower.getTowerFloor().get(towerFloor).getCard()))
            return false;

        ExecuteEffects executeEffects = new ExecuteEffects( tower.getTowerFloor().get(towerFloor).getCard().getImmediateEffects(), player,game);

        return executeEffects.isAllowed();
    }


}
