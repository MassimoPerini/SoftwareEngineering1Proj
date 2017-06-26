package it.polimi.ingsw.GC_06.Client.ViewController;

import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.ConnectionMethod.ConnectionMethodView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login.LoginView;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by massimo on 16/06/17.
 */
public class ViewOrchestratorFx extends Application implements ViewOrchestrator {

    private Map<ClientStateName, FXMLView> clientStates;
    private ClientStateName currentState = ClientStateName.START;
    private static Stage stage;
    private SimpleStringProperty property;
    private ViewPresenterFx currentPresenter;

    public ViewOrchestratorFx()
    {
        property = new SimpleStringProperty();
        this.clientStates = new HashMap<>();
        for (ClientStateName stateName : ClientStateName.values()) {
            this.clientStates.put(stateName, null);
        }
        this.generateGUIView();
    }

    public void execute(String [] args)
    {
        launch(args);
    }

    private void generateGUIView()
    {
        clientStates.put(ClientStateName.LOGIN, new LoginView());
    }

    @Override
    public void change(ClientStateName state, String property) {
        //to remove
        /*
        if (state!=null && state.equals(ClientStateName.GAME_START))
        {
            BoardView boardView = new BoardView();
            BoardPresenter boardPresenter =(BoardPresenter) boardView.getPresenter();
            clientStates.put(ClientStateName.GAME_START, boardView);
        }

        if (property!=null) {
            this.property.setValue(property);
        }
        if (state == currentState || state==null)
        {
            return;
        }
        stage.getScene().setRoot(clientStates.get(state).getView());
        stage.sizeToScene();
        */
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle( "Lorenzo il Magnifico" );
        FXMLView view = new ConnectionMethodView();
        Scene scene = new Scene(view.getView());
        currentPresenter = (ViewPresenterFx) view.getPresenter();
        stage.setScene( scene);
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        ClientStateName clientStateName = (ClientStateName) arg;
    }
}
