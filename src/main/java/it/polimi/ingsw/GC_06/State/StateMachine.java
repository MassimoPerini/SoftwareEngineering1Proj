package it.polimi.ingsw.GC_06.State;

/**
 * Created by massimo on 14/05/17.
 */
public class StateMachine {
    //singleton

    private static StateMachine instance = null;


    private StateMachine(){
        super();
    }

    public static StateMachine getInstance()
    {
        if (instance==null)
            instance = new StateMachine();
        return instance;
    }

}
