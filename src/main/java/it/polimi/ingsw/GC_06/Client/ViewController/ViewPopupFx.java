package it.polimi.ingsw.GC_06.Client.ViewController;

import com.airhacks.afterburner.views.FXMLView;
import it.polimi.ingsw.GC_06.Client.Model.ClientStateName;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.CustomBonus.CustomBonusView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.ExcommunicationQuestion.ExcommunicationQuestionView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.MultipleRequirementsQuestion.MultipleRequirementsQuestionView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.ParchmentQuestion.ParchmentQuestionView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.PickAnotherCardQuestion.PickAnotherCardQuestionView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.PowerUpQuestion.PowerUpQuestionView;
import it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.PopUp.ProdHarvQuestion.ProdHarvQuestionView;
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


    final private Map<ClientStateName, Class<? extends FXMLView>> popupMap;

    public ViewPopupFx()
    {
        popupMap = new HashMap<>();
        popupMap.put(ClientStateName.CHOOSE_PERSONAL_BONUS, CustomBonusView.class);
        popupMap.put(ClientStateName.EXCOMMUNICATION, ExcommunicationQuestionView.class);
        popupMap.put(ClientStateName.ASK_PRODHARV_CARDS, ProdHarvQuestionView.class);
        popupMap.put(ClientStateName.MULTIPLE_PAYMENT, MultipleRequirementsQuestionView.class);
        popupMap.put(ClientStateName.PARCHMENT, ParchmentQuestionView.class);
        popupMap.put(ClientStateName.POWERUP, PowerUpQuestionView.class);
        popupMap.put(ClientStateName.ASK_PRODHARV_CARDS, ProdHarvQuestionView.class);
        popupMap.put(ClientStateName.CHOOSE_NEW_CARD, PickAnotherCardQuestionView.class);
    }

    private FXMLView create(ClientStateName clientStateName) {
        try {
            Class<? extends FXMLView> clazz;
            clazz = popupMap.get(clientStateName);
            FXMLView newView = clazz.newInstance();
            return newView;
        }
        catch (Exception e){}
        return  null;
    }

    private void change(final ClientStateName clientStateName) {
        FXMLView fxmlView = this.create(clientStateName);
        Platform.runLater(() -> {
            try {
                Parent parent = fxmlView.getView();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(parent));
                stage.setAlwaysOnTop(true);
                stage.show();
                stage.sizeToScene();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    @Override
    public void update(Observable o, Object arg) {
        ClientStateName clientStateName = (ClientStateName) arg;
        change(clientStateName);
    }
}
