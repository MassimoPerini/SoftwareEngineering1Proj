package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.CustomBonus;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.MessageCreator;
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

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by massimo on 28/06/17.
 */
public class CustomBonusPresenter {


    private @FXML ListView mainItems;
    private @FXML Button okButton;
    private @Inject PlayerBonusActions playerBonusActions;
    private @Inject MessageCreator messageCreator;
    private ObservableList<String> items;

    public CustomBonusPresenter()
    {
        super();
    }


    @FXML void initialize() {
        System.out.println("FXML init");

    //    mainItems = new ListView();
        mainItems.setItems(items);

        mainItems.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(getClass().getResourceAsStream("/view/resources/personal_bonus/"+name+".png")));
                    setGraphic(imageView);
                }
            }
        });
    }


    @PostConstruct
    public void init()
    {
        List<String> bonusOptions = this.playerBonusActions.getPersonalBonusOptions();
        items = FXCollections.observableList(bonusOptions);
    }


    public void handleOkPressed(ActionEvent actionEvent) {

        int index = mainItems.getSelectionModel().getSelectedIndex();
        if (index==-1)
        {
            //segnalare errore
            return;
        }
        messageCreator.setMessageClient(new DefaultAnswer(index));
        messageCreator.send();
        okButton.setDisable(true);
        mainItems.setDisable(true);

        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
