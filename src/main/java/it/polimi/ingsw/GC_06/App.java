package it.polimi.ingsw.GC_06;

import it.polimi.ingsw.GC_06.ViewController.CmdViewController.StartViewController;
import it.polimi.ingsw.GC_06.ViewController.FxViewController.FxLoader;
import it.polimi.ingsw.GC_06.model.Loader.FileLoader;
import it.polimi.ingsw.GC_06.model.State.Game;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Work in progress");
    }
}

/**

 //Main controller
 //http://stackoverflow.com/questions/34712885/how-to-load-an-external-properties-file-from-a-maven-java-project
 //http://www.avajava.com/tutorials/lessons/how-do-i-read-a-properties-file-with-a-resource-bundle.html

 public static void main( String[] args ) throws IOException {

 //    f.writeBoard();
 //    f.writeCards();

 //   FileLoader.getFileLoader().writeBoard();
 //   FileLoader.getFileLoader().writeCards();

 Game game = new Game();
 game.addPlayer("massimo");
 game.addPlayer("pinco");
 game.start();

 //  FxLoader fxLoader = new FxLoader();
 //  fxLoader.initialize(args);
 new StartViewController().viewWillAppear();



 /*
 FxLoader fxLoader = new FxLoader();
 fxLoader.initialize(args);

 */
        //TODO Implement FIX: https://futurestud.io/tutorials/how-to-deserialize-a-list-of-polymorphic-objects-with-gson
        //TODO http://stackoverflow.com/questions/19588020/gson-serialize-a-list-of-polymorphic-objects

        //if (board.getTowers().get(0).getTowerFloors().get(0).getActionPlace() instanceof ActionPlaceFixed){


        /**
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



//} */


