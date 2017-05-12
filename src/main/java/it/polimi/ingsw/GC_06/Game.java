package it.polimi.ingsw.GC_06;

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

}
