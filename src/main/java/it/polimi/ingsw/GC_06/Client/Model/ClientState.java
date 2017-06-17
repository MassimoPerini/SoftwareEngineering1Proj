package it.polimi.ingsw.GC_06.Client.Model;

import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

import java.util.Observable;

/**
 * Created by giuseppe on 6/14/17.
 */
public class ClientState extends Observable {

    private ClientStateName state;
    private String text;
    private ViewPresenterCLI ViewPresenterCLI;

    public ClientState (ClientStateName state)
    {
        this(state, "");
    }

    public ClientState (ClientStateName state, String text)
    {
        this.state = state;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ViewPresenterCLI getViewPresenterCLI() {
        return ViewPresenterCLI;
    }

    public void notify(Object o)
    {
        setChanged();
        notifyObservers(o);
    }

    public ClientStateName getState() {
        return state;
    }
}
