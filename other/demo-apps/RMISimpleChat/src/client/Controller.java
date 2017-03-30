package client;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;


//TODO: !!!! Chiusura applicazione! Terminazione processi non riesce a rimuovere da lista server -> errore

public class Controller implements ViewControllerInt {

    private Communication c;

    @FXML private TextArea areachat;
    @FXML private TextField inputTxt;
    @FXML private TextField inputUser;



    public Controller() throws RemoteException, NotBoundException {
        super();
        c = new Communication("TEST");
    }


    @FXML
    public void initialize() throws RemoteException {
        System.out.println("View inizializzata");
        c.addView(this);
    }


    @FXML public void handleSubmitButtonAction(ActionEvent event) {
        try{
            c.sendMessage(inputTxt.getText().trim());
        }catch (RemoteException e){e.printStackTrace();}
    }

    @FXML
    public void exitApplication(ActionEvent event) throws RemoteException {     //TODO gestire errori in Communication
        c.remove();
        Platform.exit();
    }

    @FXML
    public void changeUsername (ActionEvent event) throws RemoteException {
        c.remove();
        c.register(inputUser.getText().trim());
    }

    //Callback
    public void showText(String input)
    {
        System.out.println(input);
        areachat.setText(areachat.getText()+"\n"+input);
    }






}
