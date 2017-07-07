package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.GameView;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board.BoardView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.FamilyMembers.FamilyMembersView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PlayerBoard.PlayerBoardView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 01/07/17.
 */
public class GameViewPresenter implements Observer {

    @FXML
    private BorderPane mainWindow;

    @Inject
    MainClientModel mainClientModel;


    @FXML
    void initialize() {


        ClientPlayerBoard clientPlayerBoard = mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername());
        Map<String, Object> context = new HashMap<>();
        context.put("clientPlayerBoard", clientPlayerBoard);
        PlayerBoardView playerBoardView = new PlayerBoardView(context::get);
        mainWindow.setLeft(playerBoardView.getView());



        BoardView boardView = new BoardView();
        mainWindow.setCenter(boardView.getView());

        FamilyMembersView familyMembersView = new FamilyMembersView();
        mainWindow.setRight(familyMembersView.getView());

        mainClientModel.addObserver(this);


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
            System.out.println("Ora mi sblocco");
        }
        else if (clientStateName == ClientStateName.ACTION_FINISHED)
        {
            System.out.println("Azione finita!");
        }
        else{
            System.out.println("Sono bloccato!");
        }
    }

}
