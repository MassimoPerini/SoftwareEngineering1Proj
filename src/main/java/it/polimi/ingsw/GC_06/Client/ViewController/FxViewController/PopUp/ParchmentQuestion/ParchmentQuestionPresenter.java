package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.ParchmentQuestion;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import it.polimi.ingsw.GC_06.model.Resource.ResourceSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by massimo on 08/07/17.
 */
public class ParchmentQuestionPresenter {

    @FXML private ListView optionsView;
    @FXML private Button okButton;

    @Inject private ClientNetworkOrchestrator clientNetworkOrchestrator;
    @Inject private PlayerBonusActions playerBonusActions;


    @FXML
    public void initialize()
    {
        List<ResourceSet> resourceSetList = playerBonusActions.getParchmentList();
        ObservableList<ResourceSet> items = FXCollections.observableList(resourceSetList);

        optionsView.setItems(items);

        optionsView.setCellFactory(param -> new ListCell<ResourceSet>() {
            @Override
            public void updateItem(ResourceSet resourceSet, boolean empty) {
                super.updateItem(resourceSet, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String text = "";
                    for (Resource resource : resourceSet.getResources().keySet()) {
                        text+=resource.name()+": "+resourceSet.getResources().get(resource);
                    }
                    setText(text);
                }
            }
        });


    }


    public void handleOkButtonPressed(ActionEvent actionEvent) {

        int i = optionsView.getSelectionModel().getSelectedIndex();
        if (i==-1)
        {
            return;
        }
        clientNetworkOrchestrator.send(new DefaultAnswer(i));
        ((Stage) okButton.getScene().getWindow()).close();
    }
}
