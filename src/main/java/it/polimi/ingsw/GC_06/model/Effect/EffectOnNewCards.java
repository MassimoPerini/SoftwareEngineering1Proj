package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.model.Board.TowerFloor;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.TransitionType;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnNewCards implements Effect {
    private ArrayList<TowerFloor> towerFloors;
    // uso uno scanner per imitare la view, andrà sostituito poi con la view vera
    private Scanner view = new Scanner(System.in);

    public EffectOnNewCards(ArrayList<TowerFloor> towerFloors) {
        super();
        this.towerFloors = towerFloors;
    }

    @Override
    public void execute(Player player) {
        Game.getInstance().getGameStatus().changeState(TransitionType.CHOOSING_CARD, towerFloors);
    }
}