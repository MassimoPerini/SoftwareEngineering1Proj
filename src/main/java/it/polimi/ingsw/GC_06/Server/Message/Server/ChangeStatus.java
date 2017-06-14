package it.polimi.ingsw.GC_06.Server.Message.Server;

import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
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
