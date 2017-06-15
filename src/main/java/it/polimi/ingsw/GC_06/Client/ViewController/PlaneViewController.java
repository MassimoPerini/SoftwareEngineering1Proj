package it.polimi.ingsw.GC_06.Client.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Created by massimo on 24/05/17.
 */
public class PlaneViewController {

    private int tower;
    private int plane;

    public PlaneViewController(int tower, int plane)
    {
        super();
        this.plane = plane;
        this.tower = tower;
        System.out.println("Plane control init: Tower: "+tower+" Plane: "+plane);
    }

    @FXML
    public void pickCardAction(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Information");
        alert.setHeaderText("I'm the presenter!");
        alert.setContentText("The user want to pick a card from the Tower: "+tower+" and the plane: "+plane);
        alert.showAndWait();

    }

}
