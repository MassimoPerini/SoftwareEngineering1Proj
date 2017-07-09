package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PlayerBoard;

import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.PlayerColors;
import it.polimi.ingsw.GC_06.model.Resource.Resource;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;
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
    private @FXML Label money, stone, wood, servant, military, faith, victory;


    @FXML
    public void initialize()
    {
        System.out.println("Initialize");
        clientPlayerBoard.addObserver(this);
        labelBoard.setText(clientPlayerBoard.getPlayerUsername());
        labelBoard.setTextFill(Color.web(playerColors.getPlayerColor(clientPlayerBoard.getPlayerUsername())));
        draw();

    }

    @PostConstruct
    public void init()
    {
        System.out.println("PostConstruct");
    }

    void draw()
    {

        Label [] labels = {money, stone, wood, servant, military, faith, victory};
        Resource [] res = {Resource.MONEY, Resource.STONE, Resource.WOOD, Resource.SERVANT, Resource.MILITARYPOINT, Resource.FAITHPOINT, Resource.VICTORYPOINT};

        for (int i=0;i<labels.length;i++)
        {
            if (clientPlayerBoard.getResourceSet().get(res[i])==null)
            {
                labels[i].setText("0");
            }
            else {
                labels[i].setText(String.valueOf(clientPlayerBoard.getResourceSet().get(res[i])));
            }
        }

        String [] colors = {"BLUE", "GREEN", "YELLOW", "PURPLE"};
        HBox [] container = {blueContainer, greenContainer, yellowContainer, purpleContainer};

        for (int i=0;i<container.length;i++) {
            container[i].getChildren().clear();
            List<String> colorList = clientPlayerBoard.getCards().get(colors[i]);
            if (colorList == null){
                continue;
            }
            for (int j=0;j<colorList.size();j++){
                String color = colorList.get(j);
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(getClass().getResourceAsStream("/view/cards/" + color + ".png")));
                imageView.setPreserveRatio(true);
                //    imageView.fitWidthProperty().bind(mainView.widthProperty());
                imageView.fitHeightProperty().bind(container[i].heightProperty());
                container[i].getChildren().add(imageView);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> draw());

    }
}
