package it.polimi.ingsw.GC_06.Client.ViewController;

import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.ConnectionMethod.ConnectionMethodView;
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

    private FXMLView create(ClientStateName clientStateName) throws InstantiationException, IllegalAccessException {
        Class<? extends FXMLView> clazz;
        clazz = views.get(clientStateName);
        FXMLView newView = clazz.newInstance();
        return newView;
    }


    private void generateGUIView()
    {
        views.put(ClientStateName.START, ConnectionMethodView.class);
        views.put(ClientStateName.LOGIN, LoginView.class);
        views.put(ClientStateName.GAME_INIT, GameViewView.class);
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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        FXMLView view = create(clientStateName);
                        view.getView();
                        Scene scene = new Scene(view.getView());
                    //    Observer currentPresenter = (Observer) view.getPresenter();
                    //    this.addObserver(currentPresenter);
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch(Exception e)
                    {e.printStackTrace();}
                }
            });

        }
        else
        {
            setChanged();
            notifyObservers(clientStateName);
        }
    }
}
