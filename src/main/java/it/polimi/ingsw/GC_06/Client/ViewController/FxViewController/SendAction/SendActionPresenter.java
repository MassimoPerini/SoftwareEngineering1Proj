package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SendAction;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.MessageCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 07/07/17.
 */
public class SendActionPresenter implements Observer {

    @Inject
    MessageCreator messageCreator;

    @Inject
    MainClientModel mainClientModel;

    @FXML
    Button sendAction;


    @FXML
    public void initialize()
    {
        sendAction.setDisable(true);
    }


    @PostConstruct
    public void init()
    {
        messageCreator.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        boolean activate = (boolean) arg;
        sendAction.setDisable(!activate);
    }

    public void handleOkPressed(ActionEvent actionEvent) {
        mainClientModel.changeMyState(ClientStateName.WAIT_TURN);
        messageCreator.send();
        sendAction.setDisable(true);
    }
}
