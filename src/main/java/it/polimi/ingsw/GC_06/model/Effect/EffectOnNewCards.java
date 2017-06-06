package it.polimi.ingsw.GC_06.model.Effect;

import it.polimi.ingsw.GC_06.FamilyMember;
import it.polimi.ingsw.GC_06.model.Action.PickCard.BoardActionOnTower;
import it.polimi.ingsw.GC_06.model.Board.Tower;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * Created by gabri on 06/06/2017.
 */
public class EffectOnNewCards implements Effect {
    private BoardActionOnTower boardActionOnTower;
    private Tower tower;
    private Array index;
    private FamilyMember familyMember;
    // uso uno scanner per imitare la view, andrà sostituito poi con la view vera
    private Scanner view = new Scanner(System.in);
    private int choice;

    public EffectOnNewCards(Tower tower, FamilyMember familyMember) {
        super();
        this.tower = tower;
        this.familyMember = familyMember;
    }

    @Override
    public void execute(Player player) {
        //mandare alla view l'array di index tra cui si può scegliere
        //uso la scan per simulare la view
        System.out.print("Choose a TowerFloor among the possible ones");
        choice = view.nextInt();
        boardActionOnTower = new BoardActionOnTower(player,choice,tower,familyMember);
        boardActionOnTower.execute();

    }
}
