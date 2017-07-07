package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PlayerBoard;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 07/07/17.
 */
public class PlayerBoardPresenter implements Observer {

    @Inject private ClientPlayerBoard clientPlayerBoard;

    private @FXML HBox  blueContainer, yellowContainer, purpleContainer, greenContainer;


    @FXML
    public void initialize()
    {
        System.out.println("Initialize");
        clientPlayerBoard.addObserver(this);
    }

    @PostConstruct
    public void init()
    {
        System.out.println("PostConstruct");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
