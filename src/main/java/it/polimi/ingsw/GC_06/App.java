package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.Board.ActionPlaceFixed;
import it.polimi.ingsw.GC_06.Board.Board;
import it.polimi.ingsw.GC_06.Card.DevelopmentCard;
import it.polimi.ingsw.GC_06.Effect.Effect;
import it.polimi.ingsw.GC_06.Effect.EffectOnResources;
import it.polimi.ingsw.GC_06.Loader.FileLoader;
import it.polimi.ingsw.GC_06.View.FxControl;
import it.polimi.ingsw.GC_06.View.FxLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

        FileLoader f = FileLoader.getFileLoader();
    //    f.writeBoard();
        f.writeCards();
        Board board = f.loadBoard();
        DevelopmentCard [] developmentCards = f.loadCards();

        for (int i=0;i<board.getTowers().size();i++)
        {
            board.getTowers().get(i).setCards(new ArrayList<>(Arrays.asList(developmentCards)));
            board.getTowers().get(i).shuffle();
        }

        FxLoader fxLoader = new FxLoader();
        fxLoader.initialize(args);


        //TODO Implement FIX: https://futurestud.io/tutorials/how-to-deserialize-a-list-of-polymorphic-objects-with-gson
        //TODO http://stackoverflow.com/questions/19588020/gson-serialize-a-list-of-polymorphic-objects

        //if (board.getTowers().get(0).getTowerFloors().get(0).getActionPlace() instanceof ActionPlaceFixed){


        }
        //else{
            //System.out.println("Errore, penso che sulla torre ci sia un actionplace generico");
        //}
/*
        Game gioco = Game.getInstance();
        CommandView view = new CommandView();
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
