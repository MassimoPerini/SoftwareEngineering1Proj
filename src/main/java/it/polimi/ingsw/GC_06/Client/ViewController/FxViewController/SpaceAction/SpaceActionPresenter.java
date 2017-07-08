package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientSpaceAction;
import it.polimi.ingsw.GC_06.Client.Model.PlayerColors;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.MessageCreator;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMultipleSteps;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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
    @Inject private PlayerColors playerColors;

    private Object containerId;
    private int elemId;
    private Class<? extends MessageMultipleSteps> message;

    void draw()
    {
        mainView.getChildren().clear();
        for (ClientFamilyMember clientFamilyMember : clientSpaceAction.getFamilyMembers()) {

            Canvas canvas = new Canvas(30,30);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            //    Field field = Class.forName("javafx.scene.paint.Color").getField(familyMember.getColor().toLowerCase());
            gc.setFill(Color.web(playerColors.getPlayerColor(clientFamilyMember.getPlayer())));
         //   gc.setFill(Color.BLUE);
            gc.fillOval(15, 15, 15, 15);


            mainView.getChildren().add(canvas);
        }
    }

    @FXML public void initialize()
    {
        clientSpaceAction.addObserver(this);

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
