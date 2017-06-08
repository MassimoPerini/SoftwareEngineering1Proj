package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnNewCards implements Effect {
    private List<TowerFloor> towerFloors;

    public EffectOnNewCards(List<TowerFloor> towerFloors) {
        super();
        this.towerFloors = towerFloors;
    }

    @Override
    public void execute(Player player) {
        Game.getInstance().getGameStatus().changeState(TransitionType.CHOOSING_CARD, towerFloors);
    }
}