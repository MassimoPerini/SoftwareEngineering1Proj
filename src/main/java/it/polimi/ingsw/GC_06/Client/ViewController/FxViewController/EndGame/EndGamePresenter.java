package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.EndGame;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.model.Action.EndGame.PersonalStatistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by massimo on 10/07/17.
 */
public class EndGamePresenter {
    private @FXML Button okButton;
    private @FXML ListView playerView;

    @Inject private MainClientModel mainClientModel;

    @FXML void initialize()
    {

        List<PersonalStatistics> personalStatisticsList = mainClientModel.getPersonalStatistics();

        ObservableList<String> items = FXCollections.observableArrayList();

        for (PersonalStatistics personalStatistics : personalStatisticsList) {
            String text = personalStatistics.getPlayerID()+" "+personalStatistics.getVictoryPointQuantity();
            items.add(text);
        }

        playerView.setItems(items);

        playerView.setCellFactory(param -> new ListCell<String>() {
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(name);
                }
            }
        });
    }


    public void handleOkPressed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
