package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.HeroCard;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.PlayerHeroCardChoices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by giuseppe on 7/9/17.
 */
public class HeroCardPresenter {

    @FXML
    private Button okButton;
    @FXML
    private ListView heroList;

    @Inject
    private ClientPlayerBoard clientPlayerBoard;

    @Inject
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    @FXML
    public void init() {

        ObservableList<String> listItems = FXCollections.observableList(clientPlayerBoard.getHeroCards());

        heroList.setItems(listItems);
        heroList.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String hero, boolean empty) {
                super.updateItem(hero, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/view/heroCard/" + hero  + ".jpg")));
                    setGraphic(imageView);
                }
            }
        });

    }
    public void showHeroCard(ActionEvent actionEvent) {

        int index = heroList.getSelectionModel().getSelectedIndex();
        if (index==-1)
        {
            //segnalare errore
            return;
        }

        List<Integer> heroCardSelection = new LinkedList<>();
        heroCardSelection.add(index);
        clientNetworkOrchestrator.send(new PlayerHeroCardChoices(heroCardSelection));


    }
}
