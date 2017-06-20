package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Tower.TowerView;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by massimo on 03/06/17.
 */
public class BoardPresenter {

    @FXML private HBox towerList;
    @FXML private HBox councilList;
    @FXML private VBox prodHarvList;
    @FXML private VBox marketList;

    @Inject private MainClientModel mainClientModel;

    @PostConstruct
    public void init()
    {
        for (String s : mainClientModel.getClientBoardGame().getTowersClient().keySet()) {
            Map<String, Object> context = new HashMap<>();
            context.put("color", s);
            TowerView towerView = new TowerView(context::get);
            towerList.getChildren().add(towerView.getView());
        }

    }
}
