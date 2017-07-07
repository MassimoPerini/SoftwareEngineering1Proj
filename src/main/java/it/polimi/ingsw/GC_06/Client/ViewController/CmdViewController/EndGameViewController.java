package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.model.Action.EndGame.PersonalStatistics;

import java.util.List;

/**
 * Created by giuseppe on 7/3/17.
 */
public class EndGameViewController implements ViewPresenterCLI  {

    private final CommandView commandView = new CmdView();
    private List<PersonalStatistics> personalStatistics;

    public EndGameViewController(List<PersonalStatistics> personalStatistics) {
        this.personalStatistics = personalStatistics;
    }

    @Override
    public void viewWillAppear(){
        commandView.addLocalizedText("La classifica ");

    }
}
