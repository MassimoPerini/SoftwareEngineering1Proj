package it.polimi.ingsw.GC_06.Client.ViewController.CmdViewController;

import it.polimi.ingsw.GC_06.Client.Model.MainClientModel;
import it.polimi.ingsw.GC_06.Client.Network.ClientNetworkOrchestrator;
import it.polimi.ingsw.GC_06.Client.View.CmdView;
import it.polimi.ingsw.GC_06.Client.View.CommandView;
import it.polimi.ingsw.GC_06.Client.ViewController.ViewPresenterCLI;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageEndTurn;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageMarketCouncil;
import it.polimi.ingsw.GC_06.Server.Message.Client.MessageProdHarv;
import it.polimi.ingsw.GC_06.model.BonusMalus.ActionType;

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

        System.out.println("CLIENTBOARDGAME INVOKED");
        boolean ok = false;
        while(!ok) {
            commandView.addLocalizedText("E' il tuo turno. \n  Ecco le tue scelte: \n 1)Inserire Board per mostrare la board o le board dell'utente  \n 2)Inserire Prendi Carta per prendere una carta," +
                    "\n 3)Inserisci prod o harv vuoi eseguire il raccolto o la produzione,\n 4) Inserisci Consiglio se vuoi effettuare una azione consiglio"+
                    "\n 5)Inserisci Mercato se vuoi effettuare un mercato"+ "\n 6) Inserisci Hero Card se la vuoi attivare \n 7)Scarta Carta Eroe \n 8) Passa Turno");
            String input = commandView.getString();



            if (input.equals("Board")) {
                BoardStatusViewController boardStatusViewController = new BoardStatusViewController(mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard());
                boardStatusViewController.viewWillAppear();
            }

          /*  if (input.equals("d")) {
            }*/

            if (input.equals("Prendi Carta")) {
                TutorialPickCard tutorialPickCard = new TutorialPickCard(commandView, mainClientModel.getClientBoardGame(), mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()), clientNetworkOrchestrator);        //Probabilmente l'interfaccia è inutile
                tutorialPickCard.viewWillAppear();
                ok = true;
            }

            if(input.equals("prod")){
                ActionType actionType = ActionType.BOARD_ACTION_ON_PROD;
                ActionType actionTypeProd = ActionType.PRODUCTION_ACTION;
                commandView.addLocalizedText("1)Scegli prod = 0 altrimenti harv = 1\n" +
                        "2)Scegli il tuo familiare\n" +
                        "3)Scegli slot piccolo  = 0 altimenti il grande = 1\n" +
                        "4)Inserisci il valore di power up\n");
                String[] inp = commandView.getString().split(" ");
                MessageProdHarv messageProdHarv = new MessageProdHarv(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]),Integer.parseInt(inp[3]),actionType);
                messageProdHarv.setStartProdHarv(actionTypeProd);
                clientNetworkOrchestrator.send(messageProdHarv);
                ok = true;
            }

            if(input.equals("harv")){
                ActionType actionType = ActionType.BOARD_ACTION_ON_HARV;
                ActionType actionTypeStartProdHarv = ActionType.HARVEST_ACTION;
                commandView.addLocalizedText("Dammi i dati");
                String[] inp = commandView.getString().split(" ");
                MessageProdHarv messageProdHarv = new MessageProdHarv(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]),Integer.parseInt(inp[3]),actionType);
                messageProdHarv.setStartProdHarv(actionTypeStartProdHarv);
                clientNetworkOrchestrator.send(messageProdHarv);
                ok = true;
            }
            if (input.equals("Passa Turno"))
            {
                MessageEndTurn messageEndTurn = new MessageEndTurn();
                clientNetworkOrchestrator.send(messageEndTurn);
                ok = true;
            }
            if (input.equals("Consiglio"))
            {
                commandView.addLocalizedText("slotMarketCouncil indiceSlot familiare powerup");
                ActionType actionType = ActionType.COUNCIL_ACTION;
                String[] inp = commandView.getString().split(" ");
                MessageMarketCouncil messageMarketCouncil = new MessageMarketCouncil(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]), Integer.parseInt(inp[3]),actionType);
                clientNetworkOrchestrator.send(messageMarketCouncil);
                ok = true;
            }
            if (input.equals("Mercato"))
            {
                commandView.addLocalizedText("slotMarketCouncil indiceSlot familiare powerup");
                ActionType actionType = ActionType.BOARD_ACTION_ON_MARKET;
                String[] inp = commandView.getString().split(" ");
                MessageMarketCouncil messageMarketCouncil = new MessageMarketCouncil(Integer.parseInt(inp[0]),Integer.parseInt(inp[1]),Integer.parseInt(inp[2]), Integer.parseInt(inp[3]),actionType);
                clientNetworkOrchestrator.send(messageMarketCouncil);
                ok = true;
            }

            if(input.equals("Hero Card")){
                // String[] inp = commandView.getString().split(" ");
                PlayHeroCardViewController playHeroCardViewController = new PlayHeroCardViewController(mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()), clientNetworkOrchestrator);
                playHeroCardViewController.viewWillAppear();
                ok = true;
            }

            if(input.equals("7")){

            DiscardHeroCardViewController discardHeroCardViewController = new DiscardHeroCardViewController(mainClientModel.getClientPlayerBoard(mainClientModel.getMyUsername()), clientNetworkOrchestrator);
            discardHeroCardViewController.viewWillAppear();
            ok = true;
            }
        }

    }

    public void run(){

    }
}
