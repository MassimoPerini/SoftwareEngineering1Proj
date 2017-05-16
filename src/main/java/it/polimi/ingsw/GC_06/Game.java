package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Board.Board;
import it.polimi.ingsw.GC_06.Dice.DiceSet;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.Scanner;

/**
 * Created by massimo on 12/05/17.
 */
public class Game {

    //SINGLETON

    private static Game instance = null;



    private Game ()
    {
        super();
    }

    public static Game getInstance()
    {
        if (instance==null)
        {
            instance = new Game ();
        }
        return instance;
    }

    public void start()
    {
        System.out.print("Benvenuto nel gioco di Lorenzo il magnifico\nSpero ti possa divertire più di quanto mi sono divertito io\n");

        //Model init
        DiceSet diceSet = new DiceSet();
        Player player1 = new Player(PlayerId.BLUE, new ResourceSet(), diceSet.createFamilyMembers(true));
        Board b = new Board();
        
        System.out.println("What do you want to do? 1: posiziona familiare, 2: incrementa valore familiare, 3: Gioca carta eroe");
        System.out.println("Che familiare vuoi usare? Questi sono i valori[0-3]:");
        System.out.println(player1.getFamilyMembers().toString());
        Scanner input = new Scanner (System.in);
        FamilyMember familiare = player1.getFamilyMembers()[input.nextInt()];

        System.out.println("Dove lo vuoi mettere? Torri[0-3]:");


    }

}
