package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.ExcommunicationQuestion;

import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PlayerChoiceExcommunication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.inject.Inject;

/**
 * Created by massimo on 09/07/17.
 */
public class ExcommunicationQuestionPresenter {

    @FXML private Button okButton;

    @FXML private Button noButton;

    @Inject
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    public void handleYesPressed(ActionEvent actionEvent) {
        clientNetworkOrchestrator.send(new PlayerChoiceExcommunication(true));
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    public void handleNoPressed(ActionEvent actionEvent) {
        clientNetworkOrchestrator.send(new PlayerChoiceExcommunication(false));
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }
}
