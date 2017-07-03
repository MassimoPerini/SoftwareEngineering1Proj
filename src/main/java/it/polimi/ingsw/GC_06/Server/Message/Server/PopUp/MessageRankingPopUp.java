package it.polimi.ingsw.GC_06.Server.Message.Server.PopUp;

import it.polimi.ingsw.GC_06.Client.ClientController;
import it.polimi.ingsw.GC_06.Server.Message.MessageServer;
import it.polimi.ingsw.GC_06.model.Action.EndGame.PersonalStatistics;

import java.util.Collections;
import java.util.List;

/**
 * Created by giuseppe on 7/3/17.
 */
public class MessageRankingPopUp implements MessageServer {

    private List<PersonalStatistics> personalStatistics;

    public MessageRankingPopUp(List<PersonalStatistics> personalStatistics) {

        this.personalStatistics = Collections.unmodifiableList(personalStatistics);
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.getMainClientModel().setPersonalStatistics(personalStatistics);
    }
}
