package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.Server.Message.Server.PopUp.MessagePickAnotherCard;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnNewCards implements Effect {
    private Map<String, List<Integer>> towerFloors;

    public EffectOnNewCards(Map<String, List<Integer>> towerFloors) {
        super();
        this.towerFloors = towerFloors;
    }

    @Override
    public void execute(Player player,Game game) {
        MessagePickAnotherCard messagePickAnotherCard = new MessagePickAnotherCard(towerFloors);
        game.getGameStatus().changeState(TransitionType.BONUS_CARD, messagePickAnotherCard);
    }
}