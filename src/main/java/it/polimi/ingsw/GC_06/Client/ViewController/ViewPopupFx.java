package it.polimi.ingsw.GC_06.Client.ViewController;

import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.CustomBonus.CustomBonusView;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by massimo on 28/06/17.
 */
public class ViewPopupFx implements Observer {


    final private Map<ClientStateName, Class<? extends FXMLView>> codecLibrary;

    public ViewPopupFx()
    {
        codecLibrary = new HashMap<>();
        codecLibrary.put(ClientStateName.CHOOSE_PERSONAL_BONUS, CustomBonusView.class);
    }

    private FXMLView create(ClientStateName clientStateName) throws InstantiationException, IllegalAccessException {
        Class<? extends FXMLView> clazz;
        clazz = codecLibrary.get(clientStateName);
        FXMLView newView = clazz.newInstance();
        return newView;
    }

    private void change(final ClientStateName clientStateName) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                test(clientStateName);
            }
        });

    }

    private void test(ClientStateName clientStateName)
    {
        try {
            FXMLView fxmlView = this.create(clientStateName);
            Parent parent = fxmlView.getView();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(parent));
            stage.show();
            stage.sizeToScene();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        ClientStateName clientStateName = (ClientStateName) arg;
        change(clientStateName);
    }
}
