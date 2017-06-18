package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.ClientBoardGame;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

/**
 * Created by massimo on 01/06/17.
 */
public class BoardStatusViewController implements ViewPresenterCLI {

    private ClientBoardGame clientBoardGame;

    public BoardStatusViewController(ClientBoardGame clientBoardGame)
    {
        this.clientBoardGame = clientBoardGame;
    }

    @Override
    public void viewWillAppear() {
        System.out.println("CLIENTBOARDGAME INVOKED");
    }

    @Override
    public void addText(String txt) {

    }
}
