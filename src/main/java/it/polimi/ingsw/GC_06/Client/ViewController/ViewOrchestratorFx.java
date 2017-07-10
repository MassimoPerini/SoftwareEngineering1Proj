package it.polimi.ingsw.GC_06.Client.ViewController;

import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.ConnectionMethod.ConnectionMethodView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.EndGame.EndGameView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.GameView.GameViewView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.Login.LoginView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by massimo on 16/06/17.
 * This class is responsible for managing the general behaviour of the view (GUI version)
 */
public class ViewOrchestratorFx extends Observable implements ViewOrchestrator {

    private Map<ClientStateName, Class<? extends FXMLView>> views;
    private Stage stage;

    public ViewOrchestratorFx(Stage stage)
    {
        this.stage = stage;
        this.views = new HashMap<>();
        this.generateGUIView();
    }

    private FXMLView create(ClientStateName clientStateName) {
        try {
            Class<? extends FXMLView> clazz;
            clazz = views.get(clientStateName);
            FXMLView newView = clazz.newInstance();
            return newView;
        }
        catch (Exception e)
        {
            System.out.println("Error with reflect");
        }
        return null;
    }


    private void generateGUIView()
    {
        views.put(ClientStateName.START, ConnectionMethodView.class);
        views.put(ClientStateName.LOGIN, LoginView.class);
        views.put(ClientStateName.GAME_INIT, GameViewView.class);
        views.put(ClientStateName.END_GAME, EndGameView.class);
    }

    @Override
    public void change(ClientStateName state, String property) {
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public void update(Observable o, Object arg) {
        ClientStateName clientStateName = (ClientStateName) arg;

        if (views.get(clientStateName)!= null)
        {
            FXMLView view = create(clientStateName);
            view.getView();
            Scene scene = new Scene(view.getView());
        //    Observer currentPresenter = (Observer) view.getPresenter();
        //    this.addObserver(currentPresenter);
            Platform.runLater(() -> {
                try {

                    stage.setScene(scene);
                    stage.sizeToScene();
                    stage.show();
                }
                catch(Exception e)
                {e.printStackTrace();}
            });

        }
        else
        {
            setChanged();
            notifyObservers(clientStateName);
        }
    }
}
