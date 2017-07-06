package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.DefaultAnswer;

/**
 * Created by massimo on 28/06/17.
 */
public class AskUserPersonalBonus implements ViewPresenterCLI {

    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private final CommandView cmdView = new CmdView();
    private final PlayerBonusActions playerBonusActions;

    public AskUserPersonalBonus (ClientNetworkOrchestrator clientNetworkOrchestrator, PlayerBonusActions playerBonusActions)
    {
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.playerBonusActions = playerBonusActions;
    }

    @Override
    public void viewWillAppear() throws InterruptedException {
        cmdView.addLocalizedText("Puoi scegliere uno stupido bonus...");
        int i=0;
        for (String s : playerBonusActions.getPersonalBonusOptions()) {
            cmdView.addText(String.valueOf(i)+" - ");
            cmdView.addLocalizedText(s);
            i++;
        }
        int res = cmdView.getInt(0, i);
        DefaultAnswer defaultAnswer = new DefaultAnswer(res);
        clientNetworkOrchestrator.send(defaultAnswer);
    }

    @Override
    public void viewWillDisappear() {

    }
}
