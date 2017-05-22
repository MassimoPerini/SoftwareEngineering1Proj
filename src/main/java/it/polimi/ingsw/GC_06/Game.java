package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Board.Board;
import it.polimi.ingsw.GC_06.Dice.DiceSet;
import it.polimi.ingsw.GC_06.Resource.ResourceSet;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by massimo on 12/05/17.
 */
public class Game extends Observable{

    //SINGLETON

    private static Game instance = null;



    private Game ()
    {
        super();
    }

    public static synchronized Game getInstance()
    {
        if (instance==null)
        {
            instance = new Game ();
        }
        return instance;
    }

    public void init()
    {
        DiceSet diceSet = new DiceSet();
        Player player1 = new Player(PlayerId.BLUE, new ResourceSet(), diceSet.createFamilyMembers(true));
     //   Board b = new Board();

        setChanged();
        notifyObservers(this);          //TODO: DA NON FARE!!!! (E RIMUOVERE)
    }

    @Override
    public String toString() {
        return "Sono il model (Game) ed è stato chiamato il mio metodo toString";
    }
}
