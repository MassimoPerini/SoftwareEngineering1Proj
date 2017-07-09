package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.TowerFloor;

import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction.SpaceActionPresenter;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction.SpaceActionView;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.model.Loader.Setting;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by massimo on 01/07/17.
 */
public class TowerFloorPresenter implements Observer {

    @FXML private ImageView imageCard;
    @FXML private HBox mainView;

    @Inject private ClientTowerFloor clientTowerFloor;
    private SpaceActionPresenter spaceActionPresenter;


    @FXML
    void initialize() {

        HBox.setHgrow(mainView, Priority.ALWAYS);
        imageCard.setPreserveRatio(true);
        imageCard.fitWidthProperty().bind(mainView.widthProperty());
        imageCard.fitHeightProperty().bind(mainView.heightProperty());

    //    HBox.setHgrow(imageCard, Priority.ALWAYS);

        Map<String, Object> context = new HashMap<>();
        context.put("clientSpaceAction", clientTowerFloor.getSpaceAction());
        SpaceActionView spaceActionView = new SpaceActionView(context::get);
        spaceActionPresenter = (SpaceActionPresenter) spaceActionView.getPresenter();

        spaceActionPresenter.setContainerId(clientTowerFloor.getContainer());
        spaceActionPresenter.setElemId(clientTowerFloor.getContent());
        spaceActionPresenter.setMessage(MessageBoardActionTower.class);

        spaceActionView.getView().getStyleClass().add("space-action-floor");

        mainView.getChildren().add(spaceActionView.getView());
        clientTowerFloor.addObserver(this);
        update(null, null);
    }

    @PostConstruct
    public void init()
    {
    }

    @Override
    public void update(Observable o, Object arg)
    {
        String cardName = clientTowerFloor.getCard();
        if (cardName==null || cardName.equals(""))
            imageCard.setImage(null);
        else
            imageCard.setImage(new Image(getClass().getResourceAsStream(Setting.getInstance().getProperty("cards_root")+cardName+Setting.getInstance().getProperty("img_extension"))));
    }
}
