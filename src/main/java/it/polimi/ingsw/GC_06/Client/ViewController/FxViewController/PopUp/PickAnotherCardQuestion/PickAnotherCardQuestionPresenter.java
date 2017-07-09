package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.PickAnotherCardQuestion;

import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by massimo on 09/07/17.
 */
public class PickAnotherCardQuestionPresenter {

    @FXML private ListView optionsView;
    @FXML private Button okButton;
    @FXML private Button noButton;

    @Inject
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    @Inject private PlayerBonusActions playerBonusActions;


    @FXML
    public void initialize()
    {
        List<ClientTowerFloor> clientTowerFloorList = playerBonusActions.getPickAnotherCard();
        ObservableList<ClientTowerFloor> items = FXCollections.observableList(clientTowerFloorList);

        optionsView.setItems(items);
        optionsView.setCellFactory(param -> new ListCell<ClientTowerFloor>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(ClientTowerFloor clientTowerFloor, boolean empty) {
                super.updateItem(clientTowerFloor, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String img = clientTowerFloor.getCard();
                    imageView.setImage(new Image(getClass().getResourceAsStream("/view/cards/"+img+".png")));
                    setGraphic(imageView);
                }
            }
        });
    }


    public void handleOkPressed(ActionEvent actionEvent) {

        int i = optionsView.getSelectionModel().getSelectedIndex();
        if (i==-1)
        {
            return;
        }
        clientNetworkOrchestrator.send(new DefaultAnswer(i));
        ((Stage) okButton.getScene().getWindow()).close();
    }

    public void handleNoPressed(ActionEvent actionEvent) {
        clientNetworkOrchestrator.send(new DefaultAnswer(-1));
        ((Stage) okButton.getScene().getWindow()).close();
    }
}
