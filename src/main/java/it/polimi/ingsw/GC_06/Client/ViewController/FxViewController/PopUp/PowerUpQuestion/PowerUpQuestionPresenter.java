package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.PowerUpQuestion;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.inject.Inject;

/**
 * Created by massimo on 08/07/17.
 */
public class PowerUpQuestionPresenter {

    @FXML private Button okButton;
    @FXML private Button noButton;
    @FXML private TextField userInput;

    @Inject private PlayerBonusActions playerBonusActions;
    @Inject private ClientNetworkOrchestrator clientNetworkOrchestrator;


    public void handleOkButtonPressed(ActionEvent actionEvent) {
        try {
            int val = Integer.parseInt(userInput.getText());
            if (val<0)
                return;

            clientNetworkOrchestrator.send(new DefaultAnswer(val));
            ((Stage) okButton.getScene().getWindow()).close();
        }
        catch (NumberFormatException n)
        {
            return;
        }
    }

    public void handleNoButtonPressed(ActionEvent actionEvent) {
        clientNetworkOrchestrator.send(new DefaultAnswer(0));
        ((Stage) noButton.getScene().getWindow()).close();
    }
}
