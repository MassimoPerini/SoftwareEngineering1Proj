package it.polimi.ingsw.GC_06.Client.ViewController;

/**
 * Created by massimo on 21/05/17.
 */
public interface ViewPresenterCLI extends Runnable {
    void viewWillAppear();
    void addText(String txt);
    void viewWillDisappear();
}
