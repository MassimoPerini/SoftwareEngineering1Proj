package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Control.FxControl;
import it.polimi.ingsw.GC_06.Control.TerminalControl;
import it.polimi.ingsw.GC_06.Loader.FileLoader;
import it.polimi.ingsw.GC_06.View.CmdView;
import it.polimi.ingsw.GC_06.Control.FxLoader;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{

    //Main controller
    //http://stackoverflow.com/questions/34712885/how-to-load-an-external-properties-file-from-a-maven-java-project
    //http://www.avajava.com/tutorials/lessons/how-do-i-read-a-properties-file-with-a-resource-bundle.html

    public static void main( String[] args ) throws IOException {


        Game gioco = Game.getInstance();
        CmdView view = new CmdView();
        TerminalControl c = new TerminalControl(gioco, view);

        FxLoader fxLoader = new FxLoader();
        FxControl fxControl = new FxControl();
        fxLoader.setFxControl(fxControl);
        fxLoader.show("");
    //    view.startMessage();

        FileLoader f = FileLoader.getFileLoader();
    //    f.writeResources();
        f.loadResources();


    }
}
