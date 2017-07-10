package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.Disconnected;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.MessageComeBack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.inject.Inject;

/**
 * Created by massimo on 10/07/17.
 */
public class DisconnectedPresenter {
    @FXML private Button mainButton;
    @FXML private Label mainText;

    @Inject private MainClientModel mainClientModel;
    @Inject private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private boolean me = false;

    @FXML void initialize()
    {
        me = mainClientModel.getPlayerBonusActions().getUserDisconnected().equals(mainClientModel.getMyUsername());
        if (me)
        {
            mainText.setText("You have been disconnected. Press the button to come back");
        }
        else {
            mainText.setText(mainClientModel.getPlayerBonusActions().getUserDisconnected()+" has been disconnected");
        }
    }

    public void handleOkPressed(ActionEvent actionEvent) {
        if (me)
        {
            MessageComeBack messageComeBack = new MessageComeBack();
            clientNetworkOrchestrator.send(messageComeBack);
        }
        ((Stage) mainButton.getScene().getWindow()).close();
    }
}
