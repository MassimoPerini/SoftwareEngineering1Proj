package it.polimi.ingsw.GC_06.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Network.Client.ClientOutputHandler;
import it.polimi.ingsw.GC_06.View.CommandView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 12/06/17.
 */
public class MainController implements Observer {

    private ClientOutputHandler output;

    public MainController(ClientOutputHandler clientOutputHandler)
    {
        this.output = clientOutputHandler;
    }

    public void start()
    {
        new LoginViewController(output).viewWillAppear();
    }

    public void setClientOutput(ClientOutputHandler output)
    {
        this.output = output;
    }

    public void publishView (CommandView commandView)
    {
    //    commandView.setOutput
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println("Notified");

    }

}
