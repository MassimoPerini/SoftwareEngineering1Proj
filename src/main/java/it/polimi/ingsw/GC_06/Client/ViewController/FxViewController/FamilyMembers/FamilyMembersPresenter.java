package it.polimi.ingsw.GC_06.Client.ViewController.FxViewController.FamilyMembers;

import it.polimi.ingsw.GC_06.Client.Model.ClientFamilyMember;
import it.polimi.ingsw.GC_06.Client.Model.ClientPlayerBoard;
import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by massimo on 05/07/17.
 */
public class FamilyMembersPresenter {


    @FXML private VBox familyMembersView;
    @FXML private BorderPane mainView;

    @Inject
    private MainClientModel mainClientModel;


    @FXML
    public void initialize()
    {
        ClientPlayerBoard clientPlayerBoard = mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername());
        List<ClientFamilyMember> clientFamilyMember = clientPlayerBoard.getFamilyMembers();

        for (ClientFamilyMember familyMember : clientFamilyMember) {
            Button button = new Button(familyMember.getColor()+" "+familyMember.getValue());
            familyMembersView.getChildren().add(button);
            button.setOnAction(event -> System.out.println("Bottone familiare!"));
        }
    }


}
