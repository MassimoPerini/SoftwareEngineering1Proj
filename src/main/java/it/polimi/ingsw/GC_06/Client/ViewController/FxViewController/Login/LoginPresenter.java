package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login;

import it.polimi.ingsw.GC_06.Client.Network.ClientOrchestrator;
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
public class LoginPresenter implements Observer, Initializable{

    @FXML private TextField inputUsernameLogin;
    @FXML private Label statusLogin;
    @Inject private ClientOrchestrator clientOrchestrator;

    public void handleSubmitLogin(ActionEvent event) {
        //Invoked when user wants to login
        String username = inputUsernameLogin.getText();
        clientOrchestrator.send(username);

    }

    @Override
    public void update(Observable o, Object arg) {
        //
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Init the presenter...
    }

    @PostConstruct
    public void init() {
        System.out.println("Init after Dep. Injection");
    }
}
