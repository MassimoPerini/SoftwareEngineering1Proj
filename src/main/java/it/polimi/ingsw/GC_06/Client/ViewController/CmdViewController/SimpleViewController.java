package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;

/**
 * Created by massimo on 24/06/17.
 */
public class SimpleViewController implements ViewPresenterCLI {

    private String text;

    public SimpleViewController (String text)
    {
        this.text = text;
    }

    @Override
    public void viewWillAppear() {
        System.out.println(text);
    }

    @Override
    public void addText(String txt) {

    }

    @Override
    public void viewWillDisappear() {

    }
}
