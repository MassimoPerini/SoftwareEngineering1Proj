package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Main;

import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board.BoardView;
import it.polimi.ingsw.GC_06.model.State.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import javax.inject.Inject;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by massimo on 03/06/17.
 * @author massimo
 * This is the "Main View" presenter
 * It uses FXML and @INJECT directives.
 */
public class MainPresenter implements Initializable, Observer {

    @FXML
    private AnchorPane mainContent;

    @FXML
    private FlowPane buttonBox;

    @Inject
    private Game game;

    private Button[] buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BoardView boardView = new BoardView();
        //mainContent.getChildren().add(boardView.getView());

        buttons = new Button [game.getGameStatus().getPlayers().size()];

        for (int i=0;i<buttons.length;i++) {
            Button button = new Button("Show cards user " + game.getGameStatus().getPlayers().get(i).getPLAYER_ID());
            buttons[i] = button;
            button.setOnAction(arg0 -> {
                try {
                    //Set open new windows with player x cards
                } catch (Exception e) {

                }
            });
        }
        buttonBox.getChildren().addAll(buttons);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Status requres action
    }
}
