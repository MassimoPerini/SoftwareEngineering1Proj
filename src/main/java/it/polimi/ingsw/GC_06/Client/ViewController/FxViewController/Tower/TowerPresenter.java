package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Tower;

import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.TowerFloor.TowerFloorPresenter;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.TowerFloor.TowerFloorView;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by massimo on 20/06/17.
 */
public class TowerPresenter implements Observer {

    @Inject private MainClientModel mainClientModel;
    @FXML private HBox mainBox;
    private Map<String, List<ClientTowerFloor>> towers;
    private Map<String, TowerFloorPresenter> towerFloors;
    private static final int spazio_piani = 5;



    @FXML
    void initialize() {

        for (String s : towers.keySet()) {
            //Nuova torre
            List<TowerFloorPresenter> towerFloorPresenters = new LinkedList<>();

            VBox floorsView = new VBox();

            for (ClientTowerFloor clientTowerFloor : towers.get(s)) {

                //Inject del parametro
                Map<String, Object> context = new HashMap<>();
                context.put("clientTowerFloor", clientTowerFloor);
                TowerFloorView towerFloorView = new TowerFloorView(context::get);

                floorsView.getChildren().add(towerFloorView.getView());
                towerFloorPresenters.add((TowerFloorPresenter) towerFloorView.getPresenter());
            }

            floorsView.setAlignment(Pos.TOP_LEFT);
            HBox.setHgrow(floorsView, Priority.ALWAYS);
            floorsView.getStyleClass().add("tower");

            mainBox.getChildren().add(floorsView);

        }

        mainClientModel.getClientBoardGame().addObserver(this);
    }

    @PostConstruct public void init()
    {
        towers = mainClientModel.getClientBoardGame().getTowersClient();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
