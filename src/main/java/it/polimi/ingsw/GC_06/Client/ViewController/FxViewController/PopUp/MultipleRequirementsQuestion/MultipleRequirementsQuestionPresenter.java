package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.MultipleRequirementsQuestion;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;
import it.polimi.ingsw.GC_06.model.Card.Requirement;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by massimo on 09/07/17.
 */
public class MultipleRequirementsQuestionPresenter {

    @FXML Button okButton;
    @FXML ListView optionsView;

    @Inject ClientNetworkOrchestrator clientNetworkOrchestrator;
    @Inject PlayerBonusActions playerBonusActions;


    @FXML
    public void initialize()
    {
        List<Requirement> requirementList = playerBonusActions.getRequirementCard();
        List<String> txtRep = new LinkedList<>();
        for (Requirement requirement : requirementList) {
            String txt = "Requirements: ";
            for (Resource resource : requirement.getRequirements().getResources().keySet()) {
                txt+=resource.name()+": "+requirement.getRequirements().getResources().get(resource)+", ";
            }
            txt+="Cost ";
            for (Resource resource : requirement.getCost().getResources().keySet()) {
                txt+=resource.name()+": "+requirement.getCost().getResources().get(resource)+", ";
            }
            txtRep.add(txt);
            txt="";
        }

        ObservableList<String> items = FXCollections.observableList(txtRep);

        optionsView.setItems(items);

        optionsView.setCellFactory(param -> new ListCell<String>() {
            @Override
            public void updateItem(String requirement, boolean empty) {
                super.updateItem(requirement, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(requirement);
                }
            }
        });

    }

    public void handleOkPressed(ActionEvent actionEvent) {
        int index = optionsView.getSelectionModel().getSelectedIndex();
        if (index==-1)
        {
            //segnalare errore, nessuna selezione
            return;
        }
        clientNetworkOrchestrator.send(new DefaultAnswer(index));
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
