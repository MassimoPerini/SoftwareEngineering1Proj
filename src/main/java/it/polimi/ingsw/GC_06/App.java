package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.ViewController.CmdViewController.StartViewController;
import it.polimi.ingsw.GC_06.ViewController.NavigationController;
import it.polimi.ingsw.GC_06.model.Board.Board;
import it.polimi.ingsw.GC_06.model.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.model.Dice.DiceSet;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.View.FxLoader;
import it.polimi.ingsw.GC_06.model.State.Game;
import it.polimi.ingsw.GC_06.model.State.GameStatus;
import it.polimi.ingsw.GC_06.model.playerTools.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    //    f.writeBoard();
    //    f.writeCards();
        Game game = Game.getInstance();
        game.addPlayer("massimo");
        NavigationController.getInstance().pushViewController(new StartViewController());

/*
        FxLoader fxLoader = new FxLoader();
        fxLoader.initialize(args);

*/
        //TODO Implement FIX: https://futurestud.io/tutorials/how-to-deserialize-a-list-of-polymorphic-objects-with-gson
        //TODO http://stackoverflow.com/questions/19588020/gson-serialize-a-list-of-polymorphic-objects

        //if (board.getTowers().get(0).getTowerFloors().get(0).getActionPlace() instanceof ActionPlaceFixed){


        }
        //else{
            //System.out.println("Errore, penso che sulla torre ci sia un actionplace generico");
        //}
/*
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
*/

    }
//}
