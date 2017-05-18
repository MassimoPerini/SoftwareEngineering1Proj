package it.polimi.ingsw.GC_06.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 * Created by massimo on 18/05/17.
 */
public class FxControl {

    public FxControl()
    {
        super();
    }

    @FXML
    public void initialize() {
        System.out.println("View inizializzata");
    }

    @FXML
    public void handleNewCardWithResource(ActionEvent event) {
        System.out.println("Vuoi prendere carta e risorsa");
    }

    @FXML
    public void handleNewCard(ActionEvent event)
    {

    }

    @FXML
    public void handleProduction (ActionEvent event)
    {

    }

    @FXML
    public void handleCouncil (ActionEvent event)
    {

    }

    @FXML
    public void handleMarket (ActionEvent event)
    {

    }
}