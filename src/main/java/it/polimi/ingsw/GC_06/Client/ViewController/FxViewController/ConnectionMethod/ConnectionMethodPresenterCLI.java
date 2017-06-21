package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.ConnectionMethod;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login.LoginView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewOrchestratorFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by massimo on 15/06/17.
 */
public class ConnectionMethodPresenterCLI {

    @Inject
    ClientNetworkOrchestrator clientNetworkOrchestrator;
    @Inject
    MainClientModel mainClientModel;

    @Inject
    ViewOrchestratorFx viewOrchestratorFx;

    @FXML
    private Button socket;
    @FXML
    private Button rmi;

    public void handleSocketConnection(ActionEvent event) throws IOException {
        clientNetworkOrchestrator.useSocket();
    //    viewOrchestratorFx.change(ClientStateName.LOGIN, "");


        Stage stage=(Stage)socket.getScene().getWindow();
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getView());
        stage.setScene(scene);
        stage.show();

    }

    public void handleRMIConnection(ActionEvent event) {


        //   mainClientModel.changeState(ClientStateName.LOGIN);
    }

}
