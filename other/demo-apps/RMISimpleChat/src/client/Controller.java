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



    public Controller(Communication c) throws RemoteException, NotBoundException {
        super();
        this.c = c;
        Platform.setImplicitExit(true);
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
