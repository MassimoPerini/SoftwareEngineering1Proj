package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.GameView;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board.BoardPresenter;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board.BoardView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.FamilyMembers.FamilyMembersPresenter;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.FamilyMembers.FamilyMembersView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PlayerBoard.PlayerBoardPresenter;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PlayerBoard.PlayerBoardView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
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

    private PlayerBoardPresenter playerBoardPresenter;
    private FamilyMembersPresenter familyMembersPresenter;
    private BoardPresenter boardPresenter;
    private Parent boardView;
    private boolean initDisabled = true;

    @FXML
    synchronized void initialize() {


        ClientPlayerBoard clientPlayerBoard = mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername());
        Map<String, Object> context = new HashMap<>();
        context.put("clientPlayerBoard", clientPlayerBoard);
        PlayerBoardView playerBoardView = new PlayerBoardView(context::get);
        mainWindow.setLeft(playerBoardView.getView());
        playerBoardPresenter = (PlayerBoardPresenter) playerBoardView.getPresenter();


        BoardView boardView = new BoardView();
        mainWindow.setCenter(boardView.getView());
        this.boardView = boardView.getView();
        this.boardView.setDisable(true);
        boardPresenter = (BoardPresenter) boardView.getPresenter();


        FamilyMembersView familyMembersView = new FamilyMembersView();
        mainWindow.setRight(familyMembersView.getView());
        familyMembersPresenter = (FamilyMembersPresenter) familyMembersView.getPresenter();

        mainClientModel.addObserver(this);


    }

    @PostConstruct
    public void init() {

    }

    public void showAllPlayersBoard(ActionEvent actionEvent) {

    }

    @Override
    synchronized public void update(Observable o, Object arg) {
        ClientStateName clientStateName = (ClientStateName) arg;

        Platform.runLater(() -> {
            if (clientStateName == ClientStateName.MY_TURN)
            {
                boardView.setDisable(false);
                familyMembersPresenter.disableAll(false);
                System.out.println("Ora mi sblocco");
            }
            else if (clientStateName == ClientStateName.ACTION_FINISHED)
            {
                boardView.setDisable(true);
                familyMembersPresenter.disableOnlyFamilyMembers(true);
                System.out.println("Azione finita!");
            }
            else{
                boardView.setDisable(true);
                familyMembersPresenter.disableAll(true);
                System.out.println("Sono bloccato!");
            }
        });
    }

}
