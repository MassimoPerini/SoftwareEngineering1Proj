package it.polimi.ingsw.GC_06.Control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by massimo on 18/05/17.
 */
public class FxLoader extends Application {

    private FxControl fxControl;

    public FxLoader()
    {
        super();
    }

    public void show(String args)
    {
        Application.launch(args);
    }

    //TODO!!!! NON VA!!!!!!
    public void setFxControl(FxControl fxControl) {
        this.fxControl = fxControl;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new FxControl());
        loader.setLocation(getClass().getResource("/views/fxml/demoGui.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("GUI Demo Gruppo 6");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }
}
