package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientSpaceAction;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.MessageCreator;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMultipleSteps;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 03/07/17.
 */
public class SpaceActionPresenter implements Observer {

    @FXML private HBox mainView;
    @Inject private ClientSpaceAction clientSpaceAction;
    @Inject private MessageCreator messageCreator;

    private Object containerId;
    private int elemId;
    private Class<? extends MessageMultipleSteps> message;

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
            try {
                MessageMultipleSteps messageMultipleSteps = message.getDeclaredConstructor(Object.class, int.class).newInstance(containerId, elemId);
                messageCreator.setMessageClient(messageMultipleSteps);

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                System.out.println("ERROR!!! Something wrong with Reflect");
            }
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

    public void setMessage(Class<? extends MessageMultipleSteps> message) {
        this.message = message;
    }
}
