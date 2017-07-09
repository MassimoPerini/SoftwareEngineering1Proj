package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Board;

import it.polimi.ingsw.GC_06.Client.Model.ClientSpaceAction;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction.SpaceActionPresenter;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.SpaceAction.SpaceActionView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Tower.TowerView;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMarketCouncil;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageProdHarv;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by massimo on 03/06/17.
 * THIS IS THE MAIN BOARD!!!
 */
public class BoardPresenter extends Observable implements Observer {

    @FXML private VBox mainView;

    @Inject private MainClientModel mainClientModel;

    private List<List<ClientSpaceAction>> marketInfo;
    private List<List<ClientSpaceAction>> prodHarvInfo;


    @FXML void initialize() {

        TowerView towerView = new TowerView();

        mainView.getChildren().add(towerView.getView());        //added the tower view to the main view

        HBox prodMarket = new HBox();

        VBox prodHarvView = new VBox(22);
        VBox marketView = new VBox();


        Map<String, Object> context = new HashMap<>();
        context.put("clientSpaceAction", mainClientModel.getClientBoardGame().getCouncil().get(0).get(0));
        SpaceActionView councilView = new SpaceActionView(context::get);
        councilView.getView().getStyleClass().add("council-view");
        mainView.getChildren().add(councilView.getView());
        SpaceActionPresenter councilPresenter = (SpaceActionPresenter) councilView.getPresenter();
        councilPresenter.setActionType(ActionType.COUNCIL_ACTION);
        councilPresenter.setElemId(0);
        councilPresenter.setContainerId(0);
        councilPresenter.setMessage(MessageMarketCouncil.class);


        prodHarvView.getStyleClass().add("prodHarv-view");
        marketView.getStyleClass().add("market-view");
        prodMarket.getStyleClass().add("prod-market-area");

        prodMarket.getChildren().add(prodHarvView);
        prodMarket.getChildren().add(marketView);


        marketInfo = mainClientModel.getClientBoardGame().getMarket();
        prodHarvInfo = mainClientModel.getClientBoardGame().getProductionHarvest();

        int containerIndex=0;

        for (List<ClientSpaceAction> clientSpaceActions : prodHarvInfo) {
            //Prod or Harv
            HBox rowProdHarv = new HBox(35);
            prodHarvView.getChildren().add(rowProdHarv);
            boolean big = false;

            int elemIndex = 0;
            for (ClientSpaceAction clientSpaceAction : clientSpaceActions)
            {
                context = new HashMap<>();
                context.put("clientSpaceAction", clientSpaceAction);
                SpaceActionView spaceActionView = new SpaceActionView(context::get);
                SpaceActionPresenter spaceActionPresenter = (SpaceActionPresenter) spaceActionView.getPresenter();
                spaceActionPresenter.setContainerId(containerIndex);
                spaceActionPresenter.setElemId(elemIndex);
                spaceActionPresenter.setMessage(MessageProdHarv.class);

                //Mostrane una grande, l'altra normale
                if (big) {
                    spaceActionView.getView().getStyleClass().add("spaceaction-big");
                    big = false;
                }
                else{
                    spaceActionView.getView().getStyleClass().add("spaceaction");
                    big = true;
                }

                rowProdHarv.getChildren().add(spaceActionView.getView());
                elemIndex++;
            }

            elemIndex++;
        }

        containerIndex = 0;

        for (List<ClientSpaceAction> clientSpaceActions : marketInfo) {
            HBox rowMarket = new HBox(15);
            marketView.getChildren().add(rowMarket);
            int elemIndex = 0;

            for (ClientSpaceAction clientSpaceAction : clientSpaceActions) {
                context = new HashMap<>();
                context.put("clientSpaceAction", clientSpaceAction);
                SpaceActionView spaceActionView = new SpaceActionView(context::get);
                spaceActionView.getView().getStyleClass().add("spaceaction");

                SpaceActionPresenter spaceActionPresenter = (SpaceActionPresenter) spaceActionView.getPresenter();
                spaceActionPresenter.setContainerId(containerIndex);
                spaceActionPresenter.setElemId(elemIndex);
                spaceActionPresenter.setMessage(MessageMarketCouncil.class);
                spaceActionPresenter.setActionType(ActionType.BOARD_ACTION_ON_MARKET);

                rowMarket.getChildren().add(spaceActionView.getView());


                elemIndex++;
            }
            containerIndex++;
        }

        mainView.getChildren().add(prodMarket);

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
            // redraw
        }
    }
}
