package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.Error;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by massimo on 10/07/17.
 */
public class ErrorPresenter {

    @FXML private Button mainButton;
    @FXML private Label mainText;

    @FXML void initialize()
    {
        mainText.setText("Error with your action");
    }

    public void handleOkPressed(ActionEvent actionEvent) {
        ((Stage) mainButton.getScene().getWindow()).close();

    }
}
