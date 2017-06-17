package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController;

import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.ConnectionMethod.ConnectionMethodView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login.LoginView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Main.MainView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by massimo on 18/05/17.
 * @author massimo
 * Loads and shows the main JavaFx class
 * It uses Afterburner fx in order to simplify its creation.
 */
public class FxLoader extends Application {

    private Stage primaryStage1;
    private Scene currentScene;

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
        primaryStage.setTitle( "Lorenzo il Magnifico" );
        Scene scene = new Scene(new ConnectionMethodView().getView());
        primaryStage.setScene( scene);
        primaryStage1 = primaryStage;
        primaryStage.show();
        currentScene = scene;
        primaryStage1 = primaryStage;
    }

    public void changeView(Parent view)
    {
    //    Stage stage = (Stage) currentScene.getWindow();
    //    stage.setScene(new Scene(view));
    /*    primaryStage1.getScene().setRoot(view);
        primaryStage1.sizeToScene();*/
    }

}
