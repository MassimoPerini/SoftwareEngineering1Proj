package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

/**
 * Created by massimo on 10/07/17.
 */
public class ErrorViewController implements ViewPresenterCLI {

    private final CommandView commandView = new CmdView();

    @Override
    public void viewWillAppear() throws InterruptedException {
        commandView.addLocalizedText("Error with your action!");
    }
}
