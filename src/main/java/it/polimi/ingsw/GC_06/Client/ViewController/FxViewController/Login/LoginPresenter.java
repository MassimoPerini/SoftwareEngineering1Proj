package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login;

import it.polimi.ingsw.GC_06.Client.ClientInputController;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by massimo on 15/06/17.
 */
public class LoginPresenter {

    @FXML private TextField inputUsernameLogin;
    @FXML private Label statusLogin;
    @Inject private ClientInputController clientInputController;

    public void handleSubmitLogin(ActionEvent event) {
        //Invoked when user wants to login
        String username = inputUsernameLogin.getText();
        clientInputController.getClientNetworkOrchestrator().send(username);
        statusLogin.setText("Sarà avvenuto il login?");

    }


    @PostConstruct
    public void init() {
        System.out.println("Init after Dep. Injection");
    /*    clientInputController.getMainClientModel().getClientStates().get(ClientStateName.LOGIN_FAIL).addObserver(this);
        clientInputController.getMainClientModel().getClientStates().get(ClientStateName.LOGIN_SUCCESS).addObserver(this);
        */
    }

}
