package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientSpaceAction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 03/07/17.
 */
public class SpaceActionPresenter implements Observer {

    @FXML HBox mainView;
    @Inject ClientSpaceAction clientSpaceAction;

    private Object containerId;
    private int elemId;

    void draw()
    {
        mainView.getChildren().clear();
        for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {
            Label label = new Label(clientFamilyMember.getPlayer());
            mainView.getChildren().add(label);
        }
    }

    @FXML public void initialize()
    {
        mainView.setOnMouseClicked(event -> {
            System.out.println("Container "+containerId.toString()+" elem: "+elemId);
        });
    }

    @PostConstruct
    public void init()
    {
        clientSpaceAction.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> draw());
    }

    public void setContainerId(Object containerId) {
        this.containerId = containerId;
    }

    public void setElemId(int elemId) {
        this.elemId = elemId;
    }
}
