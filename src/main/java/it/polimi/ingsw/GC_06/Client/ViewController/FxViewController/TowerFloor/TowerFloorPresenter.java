package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.TowerFloor;

import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by massimo on 01/07/17.
 */
public class TowerFloorPresenter implements Observer {

    @FXML private ImageView imageCard;
    @FXML private Button btnPlaceFamilyMember;

    @Inject private ClientTowerFloor clientTowerFloor;

    public void handleFloorPressed(ActionEvent actionEvent) {

    }

    @FXML
    void initialize() {
        System.out.println("SONO UN PIANO - ");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
