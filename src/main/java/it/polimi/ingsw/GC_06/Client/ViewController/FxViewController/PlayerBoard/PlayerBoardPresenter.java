package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PlayerBoard;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.PlayerColors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 07/07/17.
 */
public class PlayerBoardPresenter implements Observer {

    @Inject private ClientPlayerBoard clientPlayerBoard;
    @Inject private PlayerColors playerColors;

    private @FXML HBox  blueContainer, yellowContainer, purpleContainer, greenContainer;
    private @FXML Label labelBoard;


    @FXML
    public void initialize()
    {
        System.out.println("Initialize");
        clientPlayerBoard.addObserver(this);
        labelBoard.setText(clientPlayerBoard.getPlayerUsername());
        labelBoard.setTextFill(Color.web(playerColors.getPlayerColor(clientPlayerBoard.getPlayerUsername())));
    }

    @PostConstruct
    public void init()
    {
        System.out.println("PostConstruct");
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void handleShowAllBoards(ActionEvent actionEvent) {
        System.out.println("All boards view!");
    }
}
