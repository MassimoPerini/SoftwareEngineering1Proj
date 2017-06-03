package it.polimi.ingsw.GC_06.ViewController.FxViewController;

import it.polimi.ingsw.GC_06.ViewController.FxViewController.Main.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by massimo on 18/05/17.
 */
public class FxLoader extends Application {

    public FxLoader()
    {
        super();
    }

    public void initialize(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MainView mainView = new MainView();
        Scene scene = new Scene( mainView.getView() );
        primaryStage.setTitle( "Lorenzo il Magnifico" );
        primaryStage.setScene( scene );
        primaryStage.show();


    }
}
