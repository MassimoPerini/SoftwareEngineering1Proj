package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageBoardActionTower;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageEndTurn;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMarketCouncil;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageProdHarv;

import java.util.concurrent.Future;

/**
 * Created by massimo on 19/06/17.
 */
public class UserActionViewController implements ViewPresenterCLI {

    private final MainClientModel mainClientModel;
    private final ClientNetworkOrchestrator clientNetworkOrchestrator;
    private Future future;
    private final CommandView commandView;


    public UserActionViewController(MainClientModel mainClientModel, ClientNetworkOrchestrator clientNetworkOrchestrator)
    {
        this.mainClientModel = mainClientModel;
        this.clientNetworkOrchestrator = clientNetworkOrchestrator;
        this.commandView = new CmdView();
    }

    @Override
    public void viewWillAppear() throws InterruptedException {
    //    ExecutorService executor = Executors.newCachedThreadPool();
    //    this.future = executor.submit(this);
        run();
    }

    @Override
    public void addText(String txt) {

    }

    @Override
    public void viewWillDisappear() {
    //    this.future.cancel(true);
    }

    public void run() throws InterruptedException{
        System.out.println("CLIENTBOARDGAME INVOKED");
        boolean ok = false;
        while(!ok) {
            commandView.addLocalizedText("E' il tuo turno. Inserire s per mostrare la board o le board dell'utente, d per tirare i dadi, p per prendere una carta," +
                    "Se vuoi eseguire il raccolto o la produzione scrivi prod, se vuoi posizionare il familiare nello aspazio consiglio/mercato scrivi m");
            String input = commandView.getString();

            if (input.equals("s")) {
                BoardStatusViewController boardStatusViewController = new BoardStatusViewController(mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard());
                boardStatusViewController.showStatus();        //NON AVERE UN ALTRO THREAD!!!
            }

          /*  if (input.equals("d")) {
                MessageThrowDice messageThrowDice = new MessageThrowDice();
                clientNetworkOrchestrator.send(messageThrowDice);
            }*/

            if (input.equals("p")) {
                TutorialPickCard tutorialPickCard = new TutorialPickCard(commandView, mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()));        //Probabilmente l'interfaccia è inutile
                String [] answers = tutorialPickCard.viewWillAppear();
                if (answers!=null) {
                    MessageBoardActionTower messageBoardActionTower = new MessageBoardActionTower(answers[0], Integer.parseInt(answers[1]), Integer.parseInt(answers[2]),Integer.parseInt(answers[3]));
                    clientNetworkOrchestrator.send(messageBoardActionTower);
                }
                ok = true;
            }

            if(input.equals("prod")){

                commandView.addLocalizedText("Dammi i dati");
                String[] inp = commandView.getString().split(" ");
                MessageProdHarv messageProdHarv = new MessageProdHarv(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]),Integer.parseInt(inp[3]));
                clientNetworkOrchestrator.send(messageProdHarv);
                ok = true;
            }
            if (input.equals("l"))
            {
                MessageEndTurn messageEndTurn = new MessageEndTurn();
                clientNetworkOrchestrator.send(messageEndTurn);
                ok = true;
            }
            if (input.equals("m"))
            {
                commandView.addLocalizedText("slotMarketCouncil indiceSlot familiare");
                String[] inp = commandView.getString().split(" ");
                MessageMarketCouncil messageMarketCouncil = new MessageMarketCouncil(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]));
                clientNetworkOrchestrator.send(messageMarketCouncil);
                ok = true;
            }
        }
    }
}
