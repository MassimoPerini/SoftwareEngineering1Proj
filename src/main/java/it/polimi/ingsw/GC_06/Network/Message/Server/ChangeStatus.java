package it.polimi.ingsw.GC_06.Network.Message.Server;

import it.polimi.ingsw.GC_06.Network.Message.MessageServer;
import it.polimi.ingsw.GC_06.ViewController.CmdViewController.LoginViewController;

/**
 * Created by massimo on 13/06/17.
 */
public class ChangeStatus implements MessageServer {
    private LoginViewController loginViewController;

    public ChangeStatus()
    {
        super();
    }

    @Override
    public void execute()
    {

    }
}
