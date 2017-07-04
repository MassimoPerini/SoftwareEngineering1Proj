package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.TowerFloor;

import it.polimi.ingsw.GC_06.Client.Model.ClientTowerFloor;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction.SpaceActionPresenter;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction.SpaceActionView;
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
        System.out.println("SONO UN PIANO - ");
        HBox.setHgrow(mainView, Priority.ALWAYS);

        Map<String, Object> context = new HashMap<>();
        context.put("clientSpaceAction", clientTowerFloor.getSpaceAction());
        SpaceActionView spaceActionView = new SpaceActionView(context::get);
        spaceActionPresenter = (SpaceActionPresenter) spaceActionView.getPresenter();

        spaceActionView.getView().getStyleClass().add("space-action-floor");

        mainView.getChildren().add(spaceActionView.getView());
    }

    @PostConstruct
    public void init()
    {
        clientTowerFloor.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        imageCard.setImage(new Image(getClass().getResourceAsStream("/view/resources/personal_bonus/"+clientTowerFloor.getCard()+".png")));
    }
}
