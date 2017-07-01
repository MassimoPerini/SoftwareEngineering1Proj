package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Tower.TowerView;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 03/06/17.
 * THIS IS THE MAIN BOARD!!!
 */
public class BoardPresenter extends Observable implements Observer {

    @FXML private VBox mainView;

    @Inject private MainClientModel mainClientModel;



    @FXML void initialize() {

        TowerView towerView = new TowerView();
        mainView.getChildren().add(towerView.getView());        //added the tower view to the main view
    }

    @PostConstruct
    public void init() {

    }


    @Override
    public void update(Observable o, Object arg) {
        ClientStateName clientStateName = (ClientStateName) arg;
        if (clientStateName == ClientStateName.MY_TURN)
        {
            //Sblocca
        }
        else{
            //.....
        }
    }
}
