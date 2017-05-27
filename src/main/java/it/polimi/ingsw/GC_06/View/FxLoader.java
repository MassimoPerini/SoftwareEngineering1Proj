package it.polimi.ingsw.GC_06.View;

import it.polimi.ingsw.GC_06.ViewController.PlaneViewController;
import it.polimi.ingsw.GC_06.ViewController.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by massimo on 18/05/17.
 */
public class FxLoader extends Application {

    private FxControl fxControl;
    private int nTowers=4;
    private int nFloors=4;
    private int nProductionAreas=2;

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

        FXMLLoader loader = new FXMLLoader();
        loader.setController(new MainController());
        Pane root = FXMLLoader.load(getClass().getResource("/views/fxml/mainBoard.fxml"));

        HBox towerArea = new HBox();
        HBox marketAndProduction = new HBox();


        Pane council = FXMLLoader.load(getClass().getResource("/views/fxml/council.fxml"));

        for (int i=0;i<nTowers;i++)
        {
            VBox tower = new VBox();
            for (int j=0;j<nFloors;j++) {
                loader = new FXMLLoader(getClass().getResource("/views/fxml/plane.fxml"));      //QUESTO VA!!!!!
                loader.setController(new PlaneViewController(i,j));
                Pane plane = loader.load();
                tower.getChildren().add(plane);
            }
            towerArea.getChildren().add(tower);
        }

        VBox productionArea = new VBox();
        marketAndProduction.getChildren().addAll(productionArea, FXMLLoader.load(getClass().getResource("/views/fxml/market.fxml")));


        for (int i=0;i<nProductionAreas;i++)
        {
            Pane p2 = FXMLLoader.load(getClass().getResource("/views/fxml/prodHarv.fxml"));
            productionArea.getChildren().add(p2);
        }

        root.getChildren().addAll(towerArea, council, marketAndProduction);

        Scene firstScene = new Scene(root, 300, 275);


        //    mainBoard.getChildren().addAll(towerArea, marketAndProduction);

        primaryStage.setTitle("GUI Demo Gruppo 6");
        primaryStage.setScene(firstScene);
        primaryStage.show();


    }
}
