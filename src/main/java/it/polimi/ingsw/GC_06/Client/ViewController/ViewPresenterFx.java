package it.polimi.ingsw.GC_06.Client.ViewController;

import java.util.Observer;

/**
 * Created by massimo on 25/06/17.
 */
public interface ViewPresenterFx extends Observer {

    void disable();
    void setText(String text);

}
