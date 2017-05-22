package it.polimi.ingsw.GC_06.Control;

import it.polimi.ingsw.GC_06.Game;
import it.polimi.ingsw.GC_06.View.CommandView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 16/05/17.
 */
public class TerminalControl implements Observer{

    private Game model;
    private CommandView view;

    public TerminalControl(Game m, CommandView v)
    {
        m.addObserver(v);       //V is an observer of model (g)
        v.addObserver(this);    //TerminalControl is an observer of CommandView
        this.model=m;

        ///???
        this.view = v;
        m.init();



    }



    @Override
    public void update(Observable o, Object arg) {
        System.out.println("TerminalControl: CommandView changed!\n This is the object passed:");
        System.out.println(arg);
    }
}
