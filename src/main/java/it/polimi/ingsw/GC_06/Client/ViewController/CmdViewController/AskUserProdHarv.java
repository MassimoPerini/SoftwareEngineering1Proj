package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.PlayerBonusActions;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.PopUp.ProdHarvAnswer;
import it.polimi.ingsw.GC_06.Server.Message.MessageClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by massimo on 23/06/17.
 */
public class AskUserProdHarv implements ViewPresenterCLI {

    private CommandView cmdView;
    private PlayerBonusActions playerBonusActions;
    private ClientNetworkOrchestrator clientNetworkOrchestrator;

    public AskUserProdHarv(PlayerBonusActions playerBonusActions, ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        cmdView = new CmdView();
        this.playerBonusActions = playerBonusActions;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
    }

    @Override
    public void viewWillAppear() {
        cmdView.addLocalizedText("Puoi scegliere vari tipi di carte produzione facontative:\n");
        Map<String, Integer> choices = new HashMap<>();
        for (String cardName : playerBonusActions.getProdHarvAsk().keySet()) {
            cmdView.addLocalizedText("\n"+cardName);
            int i=0;
            for (Integer integer : playerBonusActions.getProdHarvAsk().get(cardName)) {
                cmdView.addLocalizedText("Opzione "+i+": effetto prod/harv "+integer+"\n");
                i++;
            }
            cmdView.addLocalizedText("Inserire numero opzione o -1 per passare alla prossima carta");
            int result = cmdView.getInt(-1, i);
            if (result!=-1) {
                choices.put(cardName, result);
            }
        }
        MessageClient messageClient = new ProdHarvAnswer(choices);
        clientNetworkOrchestrator.send(messageClient);
    }

    @Override
    public void addText(String txt) {

    }

    @Override
    public void viewWillDisappear() {

    }
}
