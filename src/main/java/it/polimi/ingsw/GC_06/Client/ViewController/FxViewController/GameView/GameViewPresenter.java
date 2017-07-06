package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.GameView;

import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board.BoardView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.FamilyMembers.FamilyMembersView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 01/07/17.
 */
public class GameViewPresenter implements Observer {

    @FXML
    private BorderPane mainWindow;

    private BoardView boardView;

    @FXML
    void initialize() {
        BoardView boardView = new BoardView();
        mainWindow.setCenter(boardView.getView());

        FamilyMembersView familyMembersView = new FamilyMembersView();
        mainWindow.setRight(familyMembersView.getView());


    }

    @PostConstruct
    public void init() {

    }

    public void showAllPlayersBoard(ActionEvent actionEvent) {

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
