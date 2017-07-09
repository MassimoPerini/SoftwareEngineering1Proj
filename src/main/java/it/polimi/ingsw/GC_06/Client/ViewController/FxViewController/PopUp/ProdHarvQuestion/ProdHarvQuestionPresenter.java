package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.ProdHarvQuestion;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.ProdHarvAnswer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by massimo on 09/07/17.
 */
public class ProdHarvQuestionPresenter {

    @FXML private VBox mainContainer;

    @Inject private PlayerBonusActions playerBonusActions;
    @Inject private ClientNetworkOrchestrator clientNetworkOrchestrator;
    private Map<String, ListView> listViews = new HashMap<>();

    @FXML
    public void initialize()
    {
        Map<String, List<Integer>> cards = playerBonusActions.getProdHarvAsk();

        for (String cardName : cards.keySet())
        {
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(getClass().getResourceAsStream("/view/cards/"+cardName+".png")));
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(imageView);
            List<Integer> effOptions = cards.get(cardName);
            ObservableList<Integer> items = FXCollections.observableList(effOptions);
            ListView<Integer> list = new ListView<>();
            listViews.put(cardName, list);
            borderPane.setCenter(list);
            list.setItems(items);
            list.setCellFactory(param -> new ListCell<Integer>() {
                @Override
                public void updateItem(Integer integer, boolean empty) {
                    super.updateItem(integer, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText("Effect: "+String.valueOf(integer));
                    }
                }
            });
            mainContainer.getChildren().add(borderPane);
        }
    }


    public void handleOkPressed(ActionEvent actionEvent) {

        Map<String, Integer> userChoices = new HashMap<>();

        for (String cardName : listViews.keySet()) {
            userChoices.put(cardName, listViews.get(cardName).getSelectionModel().getSelectedIndex());
        }
        ProdHarvAnswer prodHarvAnswer = new ProdHarvAnswer(userChoices);
        clientNetworkOrchestrator.send(prodHarvAnswer);
    }

}
