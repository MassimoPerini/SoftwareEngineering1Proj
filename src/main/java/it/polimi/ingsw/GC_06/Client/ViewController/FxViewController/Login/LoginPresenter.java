package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login;

import it.polimi.ingsw.GC_06.Client.ClientInputController;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Created by massimo on 15/06/17.
 */
public class LoginPresenter implements ViewPresenterFx {

    @FXML private TextField inputUsernameLogin;
    @FXML private Label statusLogin;
    @FXML private Button buttonLogin;
    @Inject private ClientInputController clientInputController;

    public void handleSubmitLogin(ActionEvent event) {
        //Invoked when user wants to login
        String username = inputUsernameLogin.getText();
        clientInputController.getClientNetworkOrchestrator().send(username);
        statusLogin.setText("Logging in...");
    }


    @PostConstruct
    public void init() {
        System.out.println("Init after Dep. Injection");
    }

    @Override
    public void disable() {
        inputUsernameLogin.setDisable(true);
        buttonLogin.setDisable(true);
    }

    @Override
    public void setText(String text) {
        statusLogin.setText(text);
    }
}
