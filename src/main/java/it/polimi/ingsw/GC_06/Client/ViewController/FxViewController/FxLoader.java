package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController;

import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login.LoginView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Main.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by massimo on 18/05/17.
 * @author massimo
 * Loads and shows the main JavaFx class
 * It uses Afterburner fx in order to simplify its creation.
 */
public class FxLoader extends Application {

    private Stage primaryStage;

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
        this.primaryStage = primaryStage;
        FXMLView loginView = new LoginView();
        Scene scene = new Scene( loginView.getView() );
        primaryStage.setTitle( "Lorenzo il Magnifico" );
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    public void change(FXMLView view)
    {
        Scene scene = new Scene( view.getView() );
        primaryStage.setTitle( "Lorenzo il Magnifico" );
        primaryStage.setScene( scene );
        primaryStage.show();
    }

}
