package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.AllBoards;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PlayerBoard.PlayerBoardView;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by massimo on 07/07/17.
 */
public class AllBoardsPresenter {

    private @FXML FlowPane containerView;
    private @Inject MainClientModel mainClientModel;

    @FXML
    public void initialize()
    {
        for (ClientPlayerBoard clientPlayerBoard : mainClientModel.getClientPlayerBoard().values()) {
            Map<String, Object> context = new HashMap<>();
            context.put("clientPlayerBoard", clientPlayerBoard);
            PlayerBoardView playerBoardView = new PlayerBoardView(context::get);
            containerView.getChildren().add(playerBoardView.getView());
        }
    }

}
