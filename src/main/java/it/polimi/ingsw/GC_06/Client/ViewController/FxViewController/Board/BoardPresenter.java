package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board;

import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Tower.TowerView;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 03/06/17.
 */
public class BoardPresenter{

    @FXML private HBox towerList;
    @FXML private HBox councilList;
    @FXML private VBox prodHarvList;
    @FXML private VBox marketList;

    @Inject private MainClientModel mainClientModel;



    @FXML void initialize() {

    }

    @PostConstruct
    public void init() {

        System.out.println("ENTRATO NELL'INIT DELLA VIEW BOARD");

        for (Map.Entry<String, List<ClientTowerFloor>> tower : mainClientModel.getClientBoardGame().getTowersClient().entrySet()) {
            HashMap<String, Object> context = new HashMap<>();
            Map<String, List<ClientTowerFloor>> entry = new HashMap<>();
            entry.put(tower.getKey(), tower.getValue());
            List <String> test = new LinkedList<>();
            test.add(tower.getKey());
            context.put("tower", test);
            TowerView towerView = new TowerView(context::get);
            towerView.getView();
        //    towerList.getChildren().add(towerView.getView());
        }



/*
        for (String color : mainClientModel.getClientBoardGame().getTowersClient().keySet()) {
            System.out.println("Iniettando " + color);
            context.put("color", color);

        }

        for (String s : mainClientModel.getClientBoardGame().getTowersClient().keySet()) {
         //   TowerView towerView = new TowerView(context::get);
            towerList.getChildren().add(towerView.getView());
        }*/
    }

}
